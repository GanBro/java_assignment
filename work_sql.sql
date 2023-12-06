-- 人员信息表
CREATE TABLE user_info (
                           user_id INT PRIMARY KEY,
                           user_name VARCHAR(50),
                           max_books_allowed INT,
                           borrowed_books INT,
                           is_vip BOOLEAN,
                           overdue_books INT
);

-- 书本详细信息表
CREATE TABLE book_details (
                              book_id INT PRIMARY KEY,
                              book_name VARCHAR(50),
                              publisher VARCHAR(50),
                              publish_date VARCHAR(10),
                              is_borrowed BOOLEAN,
                              user_id INT,
                              start_date DATE,
                              due_date DATE
);

-- 书本信息表
CREATE TABLE book_info (
                           book_info_id INT PRIMARY KEY,
                           book_name VARCHAR(50),
                           publisher VARCHAR(50),
                           publish_date DATE,
                           total_inventory INT,
                           available_books INT
);
