<template>
    <Toast></Toast>
    <div class="card">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 16px; margin-top: 10px;">
            <FloatLabel style="flex: 1; margin-right: 16px;">
                <IconField>
                    <InputIcon class="pi pi-search" />
                    <InputText id="over_label" v-model="searchKeyword" @keyup.enter="onSearch" placeholder="搜索学号/姓名/手机号/邮箱/专业" class="w-full" />
                    <Button label="清除" @click="clear"></Button>
                </IconField>
            </FloatLabel>
            <Button label="新增学生" severity="success" icon="pi pi-plus" @click="openAddDialog"></Button>
        </div>
        <DataTable
            ref="dt"
            v-model:editingRows="editingRows"
            :value="students"
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
            <Column field="studentId" header="学号" style="width: 14%">
                <template #editor="{ data, field }"><InputText v-model="data[field]" fluid /></template>
            </Column>
            <Column field="studentName" header="姓名" style="width: 14%">
                <template #editor="{ data, field }"><InputText v-model="data[field]" fluid /></template>
            </Column>
            <Column field="studentGender" header="性别" style="width: 14%">
                <template #editor="{ data, field }"><Select v-model="data[field]" :options="genders" optionLabel="label" optionValue="value" placeholder="选择性别" fluid /></template>
                <template #body="{ data }">{{ data.studentGender === 'M' ? '男' : data.studentGender === 'F' ? '女' : data.studentGender }}</template>
            </Column>
            <Column field="studentBirthday" header="生日" style="width: 18%">
                <template #editor="{ data, field }"><InputText v-model="data[field]" fluid /></template>
            </Column>
            <Column field="studentPhone" header="手机号" style="width: 18%">
                <template #editor="{ data, field }"><InputText v-model="data[field]" fluid /></template>
            </Column>
            <Column field="studentEmail" header="邮箱" style="width: 14%">
                <template #editor="{ data, field }"><InputText v-model="data[field]" fluid /></template>
            </Column>
            <Column field="studentMajor" header="专业" style="width: 14%">
                <template #editor="{ data, field }"><InputText v-model="data[field]" fluid /></template>
            </Column>
            <Column :rowEditor="true" bodyStyle="text-align:center"></Column>
            <Column bodyStyle="text-align:center">
                <template #body="{ data }">
                    <Button size="small" icon="pi pi-trash" severity="danger" @click="onDelete(data)"/>
                </template>
            </Column>
        </DataTable>
        <div class="card flex justify-center">
            <Dialog v-model:visible="visible" modal header="新增学生" :style="{ width: '25rem' }">
                <div class="flex items-center gap-4 mb-4" >
                    <label for="studentId" class="font-semibold w-24" style="margin-right: 10px;">学生学号</label>
                    <InputText id="studentId" v-model="newStudent.studentId" class="flex-auto" autocomplete="off" />
                </div>
                <div class="flex items-center gap-4 mb-4" style="margin-top: 10px;">
                    <label for="studentName" class="font-semibold w-24" style="margin-right: 10px;">学生姓名</label>
                    <InputText id="studentName" v-model="newStudent.studentName" class="flex-auto" autocomplete="off" />
                </div>
                <div class="flex items-center gap-4 mb-4" style="margin-top: 10px;">
                    <label for="studentGender" class="font-semibold w-24" style="margin-right: 10px;">学生性别</label>
                    <Select v-model="newStudent.studentGender" :options="genders" optionLabel="label" optionValue="value" placeholder="选择性别" class="flex-auto" />
                </div>
                <div class="flex items-center gap-4 mb-4" style="margin-top: 10px;">
                    <label for="studentBirthday" class="font-semibold w-24" style="margin-right: 10px;">学生生日</label>
                    <InputText id="studentBirthday" v-model="newStudent.studentBirthday" class="flex-auto" autocomplete="off" />
                </div>
                <div class="flex items-center gap-4 mb-4" style="margin-top: 10px;">
                    <label for="studentPhone" class="font-semibold w-24" style="margin-right: 10px;">学生手机号</label>
                    <InputText id="studentPhone" v-model="newStudent.studentPhone" class="flex-auto" autocomplete="off" />
                </div>
                <div class="flex items-center gap-4 mb-4" style="margin-top: 10px;">
                    <label for="studentEmail" class="font-semibold w-24" style="margin-right: 10px;">学生邮箱</label>
                    <InputText id="studentEmail" v-model="newStudent.studentEmail" class="flex-auto" autocomplete="off" />
                </div>
                <div class="flex items-center gap-4 mb-4" style="margin-top: 10px;">
                    <label for="studentMajor" class="font-semibold w-24" style="margin-right: 10px;">学生专业</label>
                    <InputText id="studentMajor" v-model="newStudent.studentMajor" class="flex-auto" autocomplete="off" />
                </div>
                <div class="flex justify-end gap-2">
                    <Button type="button" label="取消" severity="secondary" @click="closeAddDialog" style="margin-top: 10px;margin-right: 10px;"></Button>
                    <Button type="button" label="保存" @click="saveNewStudent" :loading="saving"></Button>
                </div>
            </Dialog>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue';
import { getStudentList, updateStudent, deleteStudent, searchStudents, addStudent} from '../api/api.js';
import { useToast } from 'primevue';
import Toast from 'primevue/toast';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import InputText from 'primevue/inputtext';
import Select from 'primevue/select';
import FloatLabel from 'primevue/floatlabel';
import IconField from 'primevue/iconfield';
import InputIcon from 'primevue/inputicon';
import Dialog from 'primevue/dialog';

const students = ref([]);
const editingRows = ref([]);
const dt = ref();
const loading = ref(false);
const saving = ref(false);
const genders = ref([
    { label: '男', value: '男' },
    { label: '女', value: '女' }
]);
const toast = useToast();
const searchKeyword = ref('');
const visible = ref(false);

const newStudent = reactive({
    studentId: '',
    studentName: '',
    studentGender: '',
    studentBirthday: '',
    studentPhone: '',
    studentEmail: '',
    studentMajor: ''
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
        const res = await getStudentList();
        students.value = res.data?.data || [];
    } catch (error) {
        console.error('获取学生列表失败:', error);
        console.error('数据加载失败: ' + (error.response?.data?.msg || error.message));
        toast.add({ severity: 'error', summary: 'Error', detail: '数据加载失败', life: 2000 });
        students.value = [];
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

const onRowEditSave = async (event) => {
    const { data, newData, index } = event;
    try {
        await updateStudent(data.studentId, newData);
        toast.add({ severity: 'success', summary: 'Success', detail: '修改成功', life: 2000 });
        students.value[index] = newData;
    } catch (error) {
        console.error('更新失败: ' + (error.response?.data?.msg || error.message));
        toast.add({ severity: 'error', summary: 'Error', detail: '更新失败', life: 2000 });
        await loadData();
    }
};

const onDelete = async (studentData) => {
    if (confirm(`确定要删除学生 ${studentData.studentName} (学号: ${studentData.studentId}) 吗？`)) {
        try {
            await deleteStudent(studentData.studentId);
            toast.add({ severity: 'success', summary: 'Success', detail: '删除成功', life: 2000 });
            students.value = students.value.filter(s => s.studentId !== studentData.studentId);
        } catch (error) {
            console.error('删除失败: ' + (error.response?.data?.msg || error.message));
            toast.add({ severity: 'error', summary: 'Error', detail: '删除失败', life: 2000 });
        }
    }
};

const onSearch = async () => {
    if (!searchKeyword.value.trim()) {
        loadData();
        return;
    }
    loading.value = true;
    try {
        const res = await searchStudents(searchKeyword.value.trim());
        students.value = res.data?.data || [];
        if (students.value.length === 0) {
            toast.add({ severity: 'info', summary: '搜索结果', detail: '未找到匹配的学生', life: 2000 });
        } else {
            toast.add({ severity: 'success', summary: '搜索成功', detail: `找到 ${students.value.length} 条记录`, life: 2000 });
        }
    } catch (error) {
        toast.add({ severity: 'error', summary: '搜索失败', detail: error.response?.data?.msg || error.message, life: 2000 });
        students.value = [];
    } finally {
        loading.value = false;
    }
};

const openAddDialog = () => {
    Object.keys(newStudent).forEach(key => {
        newStudent[key] = '';
    });
    visible.value = true;
};

const closeAddDialog = () => {
    visible.value = false;
};

const saveNewStudent = async () => {
    if (!newStudent.studentId.trim()) {
        toast.add({ severity: 'warn', summary: '警告', detail: '请输入学号', life: 2000 });
        return;
    }
    if (!newStudent.studentName.trim()) {
        toast.add({ severity: 'warn', summary: '警告', detail: '请输入姓名', life: 2000 });
        return;
    }

    saving.value = true;
    try {
        await addStudent(newStudent);

        toast.add({ severity: 'success', summary: '成功', detail: '新增学生成功', life: 2000 });

        closeAddDialog();

        await loadData();
    } catch (error) {
        console.error('新增失败: ' + (error.response?.data?.msg || error.message));
        toast.add({ severity: 'error', summary: '错误', detail: error.response?.data?.msg || '新增学生失败', life: 2000 });
    } finally {
        saving.value = false;
    }
};
</script>