<template>
    <Toast></Toast>
    <div class="card">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 16px; margin-top: 10px;">
            <FloatLabel class="mb-4"">
                <IconField>
                    <InputIcon class="pi pi-search" />
                    <InputText id="over_label" v-model="searchKeyword" @keyup.enter="onSearch" placeholder="搜索学号/课程编号/成绩" class="w-full" />
                    <Button label="清除" @click="clear"></Button>
                </IconField>
            </FloatLabel>
            <Button label="新增成绩" severity="success" icon="pi pi-plus" @click="openAddDialog"></Button>
        </div>
        <DataTable
            ref="dt"
            v-model:editingRows="editingRows"
            :value="scores"
            editMode="row"
            dataKey="studentId"
            @row-edit-save="onRowEditSave"
            paginator
            :rows="10"
            :rowsPerPageOptions="[10, 20, 50]"
            tableStyle="min-width: 50rem"
            :pt="{ column: { bodycell: ({ state }) => ({ style: state['d_editing'] && 'padding-top: 0.75rem; padding-bottom: 0.75rem' }) } }"
        >
            <template #paginatorstart>
                <Button type="button" icon="pi pi-refresh" text @click="refreshData" :loading="loading" />
            </template>
            <template #paginatorend>
                <Button type="button" icon="pi pi-download" text @click="exportCSV"/>
            </template>
            <Column field="studentId" header="学号" style="width: 28%">
                <template #editor="{ data, field }"><InputText v-model="data[field]" fluid /></template>
            </Column>
            <Column field="courseId" header="课程编号" style="width: 28%">
                <template #editor="{ data, field }"><InputText v-model="data[field]" fluid /></template>
            </Column>
            <Column field="score" header="成绩" style="width: 28%">
                <template #editor="{ data, field }"><InputNumber v-model="data[field]" mode="decimal" :minFractionDigits="2" :maxFractionDigits="2" fluid /></template>
            </Column>
            <Column :rowEditor="true" style="width: 8%; min-width: 6rem" bodyStyle="text-align:center" />
            <Column bodyStyle="text-align:center" style="width: 8%; min-width: 6rem">
                <template #body="{ data }">
                    <Button size="small" icon="pi pi-trash" severity="danger" @click="onDelete(data)"/>
                </template>
            </Column>
        </DataTable>
        <div class="card flex justify-center">
            <Dialog v-model:visible="visible" modal header="新增成绩" :style="{ width: '25rem' }">
                <div class="flex items-center gap-4 mb-4" >
                    <label for="studentId" class="font-semibold w-24" style="margin-right: 10px;">学生学号</label>
                    <InputText id="studentId" v-model="newScore.studentId" class="flex-auto" autocomplete="off" />
                </div>
                <div class="flex items-center gap-4 mb-4" style="margin-top: 10px;">
                    <label for="courseId" class="font-semibold w-24" style="margin-right: 10px;">课程编号</label>
                    <InputText id="courseId" v-model="newScore.courseId" class="flex-auto" autocomplete="off" />
                </div>
                <div class="flex items-center gap-4 mb-4" style="margin-top: 10px;">
                    <label for="score" class="font-semibold w-24" style="margin-right: 10px;">学生成绩</label>
                    <InputText id="score" v-model="newScore.score" class="flex-auto" autocomplete="off" />
                </div>
                <div class="flex justify-end gap-2">
                    <Button type="button" label="取消" severity="secondary" @click="closeAddDialog" style="margin-top: 10px;margin-right: 10px;"></Button>
                    <Button type="button" label="保存" @click="saveNewScore" :loading="saving"></Button>
                </div>
            </Dialog>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, reactive} from 'vue';
import { getScoreList, updateScore, deleteScore, searchScores, addScore} from '../api/api.js';
import { useToast } from 'primevue';
import Toast from 'primevue/toast';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import InputText from 'primevue/inputtext';
import InputNumber from 'primevue/inputnumber';
import Button from 'primevue/button';
import Dialog from 'primevue/dialog';

const scores = ref([]);
const editingRows = ref([]);
const dt = ref();
const loading = ref(false);
const toast = useToast();
const searchKeyword = ref('');
const saving = ref(false);

const visible = ref(false);

const newScore = reactive({
    studentId: '',
    courseId: '',
    score: ''
});

const exportCSV = () => {
    dt.value.exportCSV();
};

const clear = () => {
    searchKeyword.value = ''
    loadData();
}

const loadData = async () => {
    loading.value = true;
    try {
        const res = await getScoreList();
        scores.value = res.data?.data || [];
    } catch (error) {
        console.error('获取成绩列表失败:', error);
        console.error('数据加载失败: ' + (error.response?.data?.msg || error.message));
        toast.add({ severity: 'error', summary: 'Error', detail: '数据加载失败', life: 2000 });
        scores.value = [];
    } finally {
        loading.value = false;
    }
};

const refreshData = async () => {
    await loadData();
    toast.add({ severity: 'success', summary: 'Success', detail: '刷新成功', life: 2000 });
};

onMounted(() => {
    loadData();
});

const onSearch = async () => {
  if (!searchKeyword.value.trim()) {
    loadData();
    return;
  }
  loading.value = true;
  try {
    const res = await searchScores(searchKeyword.value.trim());
    scores.value = res.data?.data || [];
    if (scores.value.length === 0) {
      toast.add({ severity: 'info', summary: '搜索结果', detail: '未找到匹配的成绩', life: 2000 });
    } else {
      toast.add({ severity: 'success', summary: '搜索成功', detail: `找到 ${scores.value.length} 条记录`, life: 2000 });
    }
  } catch (error) {
    toast.add({ severity: 'error', summary: '搜索失败', detail: error.response?.data?.msg || error.message, life: 2000 });
    scores.value = [];
  } finally {
    loading.value = false;
  }
};

const onRowEditSave = async (event) => {
    const { newData, index } = event;
    try {
        await updateScore(newData.studentId, newData.courseId, newData);
        toast.add({ severity: 'success', summary: 'Success', detail: '修改成功', life: 2000 });
        scores.value[index] = newData;
    } catch (error) {
        console.error('更新失败: ' + (error.response?.data?.msg || error.message));
        toast.add({ severity: 'error', summary: 'Error', detail: '更新失败', life: 2000 });
        const res = await getScoreList();
        scores.value = res.data?.data || [];
    }
};

const onDelete = async (scoreData) => {
    if (confirm(`确定要删除成绩 学号:${scoreData.studentId} 课程:${scoreData.courseId}吗？`)) {
        try {
            await deleteScore(scoreData.studentId, scoreData.courseId);
            toast.add({ severity: 'success', summary: 'Success', detail: '删除成功', life: 2000 });
            scores.value = scores.value.filter(s => !(s.studentId === scoreData.studentId && s.courseId === scoreData.courseId));
            console.log('删除成功:', scoreData.studentId, scoreData.courseId);
        } catch (error) {
            console.error('删除成绩失败:', error);
            console.error('删除失败: ' + (error.response?.data?.msg || error.message));
            toast.add({ severity: 'error', summary: 'Error', detail: '删除失败', life: 2000 });
        }
    }
};

const openAddDialog = () => {
    Object.keys(newScore).forEach(key => {
        newScore[key] = '';
    });
    visible.value = true;
};

const closeAddDialog = () => {
    visible.value = false;
};

const saveNewScore = async () => {
    if (!newScore.studentId.trim()) {
        toast.add({ severity: 'warn', summary: '警告', detail: '请输入学生学号', life: 2000 });
        return;
    }
    if (!newScore.courseId.trim()) {
        toast.add({ severity: 'warn', summary: '警告', detail: '请输入课程编号', life: 2000 });
        return;
    }
    if (!newScore.score.trim()) {
        toast.add({ severity: 'warn', summary: '警告', detail: '请输入成绩', life: 2000 });
        return;
    }

    saving.value = true;
    try {
        await addScore(newScore);

        toast.add({ severity: 'success', summary: '成功', detail: '新增课程成功', life: 2000 });

        closeAddDialog();

        await loadData();
    } catch (error) {
        console.error('新增失败: ' + (error.response?.data?.msg || error.message));
        toast.add({ severity: 'error', summary: '错误', detail: error.response?.data?.msg || '新增课程失败', life: 2000 });
    } finally {
        saving.value = false;
    }
};
</script>