
# Recruitment

## Introduce
### Overview
Đây là 1 project về lĩnh vực tuyển dụng.
### Mô tả 
![image](https://github.com/dungdinhhaha/recruitment1/assets/116552465/9c63d97e-83e3-45b6-9220-94790ca00562)

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

## Installation

Git clone

## Build docker image and run 
Vô dự án và nhập
docker-compose up
