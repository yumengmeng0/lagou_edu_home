SELECT
  id,
  course_name,
  price,
  sort_num,
  STATUS
FROM
  course
WHERE is_del = 0;



SELECT
  id,
  course_name,
  price,
  sort_num,
  STATUS
FROM
  course
WHERE is_del = 0,
AND course_name LIKE ? STATUS = ?;






# 插入课程营销信息
 INSERT INTO course (
  course_name,
  brief,
  teacher_name,
  teacher_info,
  preview_first_field,
  preview_second_field,
  discounts,
  price,
  price_tag,
  share_image_title,
  share_title,
  share_description,
  course_description,
  course_img_url,
  STATUS,
  create_time,
  update_time
)VALUES
  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);


 INSERT INTO course (
  course_name,
  brief,
  teacher_name,
  teacher_info,
  preview_first_field,
  preview_second_field,
  discounts,
  price,
  price_tag,
  share_image_title,
  share_title,
  share_description,
  course_description,
  course_img_url,
  STATUS,
  create_time,
  update_time
) VALUES
  ('课程名', 
  '简介', 
  '教师名', 
  '教师信息', 
  '共10讲', 
  '每周更新'
  88.88, 
  55.0, 
  '价格标签', 
  '图片标题', 
  '分享标题', 
  '分享介绍', 
  '课程介绍', 
  '图片地址', 
  0, 
  2022-07-14 15:02:17, 
  2022-07-14 15:02:17);
  

# 根据id查询信息
 SELECT
  id,
  course_name,
  brief,
  teacher_name,
  teacher_info,
  preview_first_field,
  preview_second_field,
  discounts,
  price,
  price_tag,
  share_image_title,
  share_title,
  share_description,
  course_description,
  course_img_url,
  STATUS
FROM
  course
WHERE id = 1;

  
  
  # 修改课程状态
  
  UPDATE course SET STATUS = ?, update_time = ? WHERE id = ?;
  
# 查询某一个课程对应的章节与课时信息
 SELECT
  cs.`id`,
  cs.`section_name`,
  cl.`theme` '课时名'
FROM
  course_section cs
  INNER JOIN course_lesson cl
    ON cs.`id` = cl.`section_id`
WHERE cs.`course_id` = 59;



# 查询对应课程的章节信息
 SELECT
  id,
  course_id,
  section_name,
  description,
  order_num,
  STATUS
FROM
  course_section
WHERE course_id = 59;


# 查询章节对应的课时信息
 SELECT
  id,
  course_id,
  section_id,
  theme,
  duration,
  is_free,
  order_num,
  STATUS
FROM
  course_lesson
WHERE section_id = 34;

# 根据课程id查询课程信息
 SELECT
  id,
  course_name
FROM
  course
WHERE id = 1;

# 插入章节信息
 INSERT INTO course_section (
  course_id,
  section_name,
  description,
  order_num,
  STATUS,
  create_time,
  update_time
)
VALUES
  (?, ?, ?, ?, ?, ?, ?);

# 修改章节信息
 UPDATE
  course_section
SET
  section_name = ?,
  description = ?,
  order_num = ?,
  update_time = ?
WHERE id = ?;


# 修改章节状态
 UPDATE
  course_section
  
SET
  STATUS = ?,
  update_time = ?
WHERE id = ?;


SELECT DATABASE();

# 添加课时信息
 INSERT INTO course_lesson (
  course_id,
  section_id,
  theme,
  duration,
  is_free,
  order_num,
  STATUS,
  create_time,
  update_time
)
VALUES(?,?,?,?,?,?,?,?,?);

# 更课时信息

UPDATE course_lesson SET  
  theme = ?,
  duration = ?,
  is_free = ?,
  order_num = ?,
  update_time = ?
WHERE id = ?; 

UPDATE course_lesson SET  
  STATUS = ?,
  update_time = ?
WHERE id = ?; 


