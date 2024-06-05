### API 명세서

| 기능       | 메서드 | 엔드포인트               | 요청 헤더                                | 요청 본문                                                                                  | 응답 상태 코드 및 본문                                                                                       |
|------------|--------|--------------------------|------------------------------------------|-------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| 댓글 작성  | POST   | /post/{postId}/comments               | Authorization: Bearer <JWT_TOKEN>        | ```json { "newsfeed_id": "bigint", "content": "varchar" } ```                             | 201 Created ```json { "id": "bigint", "newsfeed_id": "bigint", "author_id": "bigint", "content": "varchar", "likes_count": 0, "created_at": "timestamp", "updated_at": "timestamp" } ``` <br> 401 Unauthorized <br> 400 Bad Request |
| 댓글 조회  | GET    | /post/{postId}/comments/   | N/A                                      | N/A                                                                                       | 200 OK ```json { "id": "bigint", "newsfeed_id": "bigint", "author_id": "bigint", "content": "varchar", "likes_count": "bigint", "created_at": "timestamp", "updated_at": "timestamp" } ``` <br> 404 Not Found                       |
| 댓글 수정  | PUT    | /post/{postId}/comments/{commentId}   | Authorization: Bearer <JWT_TOKEN>        | ```json { "content": "varchar" } ```                                                      | 200 OK ```json { "id": "bigint", "newsfeed_id": "bigint", "author_id": "bigint", "content": "varchar", "likes_count": "bigint", "created_at": "timestamp", "updated_at": "timestamp" } ``` <br> 401 Unauthorized <br> 403 Forbidden <br> 400 Bad Request <br> 404 Not Found |
| 댓글 삭제  | DELETE | /post/{postId}/comments/{commentId}   | Authorization: Bearer <JWT_TOKEN>        | N/A                                                                                       | 204 No Content <br> 401 Unauthorized <br> 403 Forbidden <br> 404 Not Found                                                                     |

### 오류 응답 예시

| 상태 코드        | 응답 본문                                                                                     |
|------------------|------------------------------------------------------------------------------------------------|
| 401 Unauthorized | ```json { "error": "Unauthorized", "message": "Invalid or missing JWT token." } ```           |
| 403 Forbidden    | ```json { "error": "Forbidden", "message": "You do not have permission to perform this action." } ``` |
| 404 Not Found    | ```json { "error": "Not Found", "message": "The requested resource was not found." } ```      |
| 400 Bad Request  | ```json { "error": "Bad Request", "message": "Invalid request parameters or payload." } ```   |