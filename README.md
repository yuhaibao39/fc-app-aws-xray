# fc-app-aws-xray

Spring Boot + Thymeleaf 分布式链路追踪演示服务。

## 项目简介

本项目是一个基于 **Spring Boot 2.1 + Thymeleaf** 的 Web 示例应用，作为分布式链路追踪（AWS X-Ray / SkyWalking）演示系统中的服务端应用。应用通过 **SkyWalking Java Agent** 实现自动链路追踪，支持 Docker 容器化部署和 Kubernetes 编排。

## 技术栈

| 技术 | 版本 |
|------|------|
| Java | 1.8 |
| Spring Boot | 2.1.0.RELEASE |
| Thymeleaf | 模板引擎 |
| Maven | 构建工具 |
| SkyWalking | Java Agent（链路追踪） |
| Docker | 容器化 |
| Kubernetes | 编排部署 |

## 项目结构

```
fc-app-aws-xray/
├── src/main/java/com/neo/
│   ├── ThymeleafApplication.java        # 应用主入口
│   ├── model/
│   │   ├── User.java                    # 用户模型
│   │   └── ApiResponse.java             # 统一 API 响应体
│   └── web/
│       ├── HelloController.java         # 首页控制器（Thymeleaf）
│       ├── ExampleController.java       # Thymeleaf 示例控制器
│       ├── ApiController.java           # REST API 控制器
│       └── HealthController.java        # 健康检查端点
├── src/main/resources/
│   ├── application.properties           # 应用配置
│   └── templates/                       # Thymeleaf 模板文件
│       ├── hello.html
│       ├── string.html
│       ├── if.html
│       ├── list.html
│       ├── url.html
│       ├── eq.html
│       └── switch.html
├── src/test/java/com/neo/
│   └── ThymeleafApplicationTests.java   # 上下文加载测试
├── Dockerfile                           # Docker 镜像构建
├── k8s.yaml                             # Kubernetes 部署清单
└── pom.xml                              # Maven 项目配置
```

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.x

### 本地运行

```bash
# 克隆项目
git clone <repo-url> && cd fc-app-aws-xray

# 编译打包
mvn clean package -DskipTests

# 启动应用
java -jar target/spring-boot-thymeleaf-0.0.1-SNAPSHOT.jar
```

应用启动后访问: **http://localhost:9999**

### Docker 部署

```bash
# 构建镜像
docker build -t fc-app-aws-xray:latest .

# 运行容器
docker run -d -p 9999:9999 --name fc-app fc-app-aws-xray:latest
```

### Kubernetes 部署

```bash
kubectl apply -f k8s.yaml
```

---

## API 接口文档

### 一、页面接口（Thymeleaf 服务端渲染）

所有页面接口返回 `text/html`，由 Thymeleaf 模板引擎渲染。

| 方法 | 路径 | 模板 | 说明 |
|------|------|------|------|
| `GET` | `/` | hello.html | 首页，展示欢迎信息 |
| `GET` | `/string` | string.html | 字符串与表达式示例 |
| `GET` | `/if` | if.html | 条件判断 `th:if` / `th:unless` |
| `GET` | `/list` | list.html | `th:each` 循环遍历示例 |
| `GET` | `/url` | url.html | URL 表达式构建 |
| `GET` | `/eq` | eq.html | 比较运算与三元表达式 |
| `GET` | `/switch` | switch.html | `th:switch` / `th:case` 分支 |

### 二、REST API 接口（JSON）

所有 REST 接口返回 `application/json`，使用统一响应格式。

| 方法 | 路径 | 说明 |
|------|------|------|
| `GET` | `/api/users` | 获取全部用户列表 |
| `GET` | `/api/users/{index}` | 按索引获取单个用户 |
| `GET` | `/api/info` | 获取应用信息 |
| `GET` | `/api/health` | 健康检查 |

### 统一响应格式

所有 REST API 返回统一的 JSON 结构：

```json
{
  "code": 200,
  "message": "success",
  "data": {}
}
```

**响应码说明：**

| code | 含义 |
|------|------|
| 200 | 请求成功 |
| 404 | 资源未找到 |
| 500 | 服务内部错误 |

### 示例请求

#### 获取所有用户

```bash
curl -X GET http://localhost:9999/api/users
```

响应：

```json
{
  "code": 200,
  "message": "success",
  "data": [
    {"name": "大牛", "age": 12, "pass": "123456"},
    {"name": "小牛", "age": 6,  "pass": "123563"},
    {"name": "Bosicloud", "age": 66, "pass": "666666"}
  ]
}
```

#### 获取单个用户

```bash
curl -X GET http://localhost:9999/api/users/0
```

#### 健康检查

```bash
curl -X GET http://localhost:9999/api/health
```

响应：

```json
{"code": 200, "message": "success", "data": "UP"}
```

---

## 配置说明

`application.properties`:

| 配置项 | 值 | 说明 |
|--------|-----|------|
| `server.port` | `9999` | 服务端口 |
| `spring.thymeleaf.cache` | `false` | 开发时禁用模板缓存 |

---

## 链路追踪

本项目通过 **SkyWalking Java Agent** 实现自动分布式链路追踪，无需在业务代码中引入额外依赖。

Agent 在 Docker 构建阶段注入镜像，运行时通过 JVM 参数加载：

```
-javaagent:/agent/skywalking-agent.jar
-Dskywalking_config=/agent/config/agent.config
-Dskywalking.agent.service_name=poc-app
-Dskywalking.collector.backend_service=<collector-host>:11800
```

**关键参数：**

| 参数 | 说明 |
|------|------|
| `skywalking.agent.service_name` | 在 SkyWalking UI 中显示的服务名称 |
| `skywalking.collector.backend_service` | SkyWalking OAP Collector 地址 |

---

## License

Internal use for demonstration purposes.
