
# Recruitment

# Introduce
Đây là 1 project về lĩnh vực tuyển dụng 
---

title: Entity Relationship Diagram
---
erDiagram
    EMPLOYER 1--0+ JOB: post
    JOB 0+--1+ JOB_FIELD: in
    JOB 0+--1+ JOB_PROVINCE: in
    SEEKER 0+--1 JOB_PROVINCE: search-in
    SEEKER 1--0+ RESUME: has
    RESUME 0+--0+ JOB_FIELD: in
    RESUME 0+--0+ JOB_PROVINCE: search-in

    JOB {
        bigint id PK
        string name
        bigint employer_id
        string fields
        string provinces
    }

    EMPLOYER {
        bigint id PK
        string name
    }

    JOB_FIELD {
        bigint id PK
        string name
    }

    JOB_PROVINCE {
        int id PK
        string name
    }

    SEEKER {
        bigint id PK
        string name
        int province
    }

    RESUME {
        bigint id PK
        bigint seeker_id
        string title
    }

EMPLOYER

Người đăng tuyển các công việc
Một employer có thể đăng 0 hoặc nhiều job
JOB

Thông tin về công việc
Một job phải nằm ở 1 hoặc nhiều lĩnh vực job_field
Một job khi đăng cần chỉ định rõ ở 1 hoặc nhiều tỉnh thành (khu vực) job_province
SEEKER

Ngưới tìm việc
Một seeker có thể tạo 0 hoặc nhiều resume
Một seeker đang làm việc hoặc mong muốn tìm việc tại một job_province
RESUME

CV xin việc
Một resume phải được sở hữu bởi duy nhất một seeker
Một resume có thể nằm ở 0 hoặc nhiều job_field
Một resume có thể được dùng để ứng tuyển công việc tại 0 hoặc nhiều job_province
JOB_FIELD

Thông tin về các lĩnh vực
JOB_PROVINCE

Thông tin về khu vực, tỉnh thành

## Installation

Git clone

## Build docker image and run 
### Build
Vô dự án và nhập
docker-compose up
