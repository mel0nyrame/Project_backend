# 学生成绩管理系统

这是一个用于团队考核的全栈项目，用于维护学生、课程和成绩信息。仓库已将原 Spring Boot 后端与 Vue 前端统一到同一个代码库，便于克隆、配置和开发。

> English documentation（主文档）：见 [README.md](README.md)。

## 功能

- 学生管理：列表、搜索、新增、编辑、删除和 CSV 导出。
- 课程管理：列表、搜索、新增、编辑和删除。
- 成绩管理：列表、搜索、新增、编辑和删除。
- 使用 Spring Boot、MyBatis、MySQL 构建 REST API。
- 使用 Vue 3、Vite、PrimeVue、Axios 构建管理界面。

## 目录结构

```text
.
├── frontend/                 # Vue 3 + Vite 前端
├── src/main/java/            # Spring Boot API 源码
├── src/main/resources/       # 应用与 MyBatis Mapper 配置
├── src/test/                 # 后端测试
├── table.sql                 # 数据库表结构与初始化 SQL
├── pom.xml                   # Maven 构建配置
├── README.md                 # 英文文档（主文档）
└── README.zh-CN.md           # 中文文档
```

## 环境要求

- Java 21
- 项目自带的 Maven Wrapper：`./mvnw`
- Node.js 与 npm
- MySQL

## 快速开始

### 1. 准备数据库

创建名为 `school` 的 MySQL 数据库并执行 [`table.sql`](table.sql)。按本地环境修改 `src/main/resources/application.yaml` 中的连接配置，不要提交真实密码。

### 2. 启动后端

```bash
./mvnw spring-boot:run
```

API 默认地址为 `http://127.0.0.1:8080`。

### 3. 启动前端

```bash
cd frontend
npm install
npm run dev
```

Vite 开发服务器默认地址为 `http://localhost:5173`。如果后端运行在其他地址，请复制 `frontend/.env.example` 为 `frontend/.env.local`，并设置 `VITE_API_BASE_URL`。

## 验证

```bash
# 在仓库根目录执行
./mvnw test

# 在 frontend/ 目录执行
npm run build
```

## API 概览

| 资源 | 基础路径 | 支持操作 |
| --- | --- | --- |
| 学生 | `/api/student/` | 列表、搜索、新增、查看、更新、删除 |
| 课程 | `/api/course/` | 列表、搜索、新增、更新、删除 |
| 成绩 | `/api/score/` | 列表、搜索、新增、更新、删除 |

## 项目命名建议

推荐统一项目名称：**Student Score Management**。GitHub 简介建议：**Full-stack student, course, and score management system built with Spring Boot, MyBatis, MySQL, Vue 3, and PrimeVue.** 建议标签：`spring-boot`、`mybatis`、`mysql`、`vue3`、`vite`、`primevue`、`student-management`、`full-stack`。

## 许可证

项目目前尚未声明许可证。如需公开分发，请先补充许可证。
