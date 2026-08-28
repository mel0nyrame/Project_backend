import { createRouter, createWebHashHistory } from 'vue-router'
import Student from '../components/Student.vue'
import Course from '../components/Course.vue'
import Score from '../components/Score.vue'
import Layout from '../layouts/Layout.vue'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: "/",
            component: Layout,
            children: [
                {
                    path: "/student",
                    component: Student
                },
                {
                    path: "/course",
                    component: Course
                },
                {
                    path: "/score",
                    component: Score
                }
            ]
        }
    ]
})

export default router