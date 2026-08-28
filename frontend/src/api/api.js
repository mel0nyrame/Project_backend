import axios from 'axios';

const request = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || "http://127.0.0.1:8080",
    timeout:20000
})

export const getStudentList = () => {
    return request({
        url: '/api/student/',
        method: 'GET',
    });
};

export const updateStudent = (studentId, studentData) => {
    return request({
        url: `/api/student/${studentId}`,
        method: 'PUT',
        data: studentData
    });
};

export const getCourseList = () => {
    return request({
        url: '/api/course/',
        method: 'GET',
    });
}

export const updateCourse = (courseId, courseData) => {
    return request({
        url: `/api/course/${courseId}`,
        method: 'PUT',
        data: courseData
    })
}

export const getScoreList = () => {
    return request({
        url: '/api/score/',
        method: 'GET'
    });
};


export const updateScore = (studentId, courseId, data) => {
    return request({
        url: `/api/score/${studentId}/${courseId}`,
        method: 'PUT',
        data: data
    });
};

export const deleteStudent = (studentId) => {
    return request({
        url: `/api/student/${studentId}`,
        method: 'DELETE'
    });
};

export const deleteCourse = (courseId) => {
    return request({
        url: `/api/course/${courseId}`,
        method: 'DELETE'
    });
};

export const deleteScore = (studentId) => {
    return request({
        url: `/api/score/${studentId}`,
        method: 'DELETE'
    });
};

export const searchStudents = (keyword) => {
    return request({
        url: '/api/student/search',
        method: 'GET',
        params: { keyword }
    });
};

export const searchCourses = (keyword) => {
    return request({
        url: '/api/course/search',
        method: 'GET',
        params: { keyword }
    });
};

export const searchScores = (keyword) => {
    return request({
        url: '/api/score/search',
        method: 'GET',
        params: { keyword }
    });
};

export const addStudent = (studentData) => {
    return request({
        url: '/api/student/',
        method: 'POST',
        data: studentData
    })
}

export const addCourse = (courseData) => {
    return request({
        url: '/api/course/',
        method: 'POST',
        data: courseData
    })
}

export const addScore = (scoreData) => {
    return request({
        url: '/api/score/',
        method: 'POST',
        data: scoreData
    })
}
