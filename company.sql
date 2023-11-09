use company;

/* USERS TABLE */
CREATE TABLE users(
	id int primary key auto_increment,
    user_name varchar(50),
    user_pw varchar(50),
    user_email varchar(50),
    user_firstname varchar(50),
    user_lastname varchar(50),
    user_admin tinyint(1)
);

/* CITIES TABLE*/
CREATE TABLE cities(
	city_id int primary key,
    city_name varchar(50)
);

insert into cities value
(1, 'Ho Chi Minh'),
(2, 'Ha Noi'),
(3, 'Da Nang'),
(4, 'Binh Duong'),
(5, 'Dong Nai'),
(6, 'Khanh Hoa'),
(7, 'Hai Phong'),
(8, 'Long An'),
(9, 'Quang Nam'),
(10, 'Ba Ria Vung Tau'),
(11, 'Dak Lak'),
(12, 'Can Tho'),
(13, 'Binh Thuan'),
(14, 'Lam Dong'),
(15, 'Thua Thien Hue'),
(16, 'Kien Giang'),
(17, 'Bac Ninh'),
(18, 'Quang Ninh'),
(19, 'Thanh Hoa'),
(20, 'Nghe An'),
(21, 'Hai Duong'),
(22, 'Gia Lai'),
(23, 'Binh Phuong'),
(24, 'Hung Yen'),
(25, 'Binh Dinh'),
(26, 'Tien Giang'),
(27, 'Thai Binh'),
(28, 'Bac Giang'),
(29, 'Hoa Binh'),
(30, 'An Giang'),
(31, 'Vinh Phuc'),
(32, 'Tay Ninh'),
(33, 'Thai Nguyen'),
(34, 'Lao Cai'),
(35, 'Nam Dinh'),
(36, 'Quang Ngai'),
(37, 'Ben Tre'),
(38, 'Dak Nong'),
(39, 'Ca Mau'),
(40, 'Vinh Long'),
(41, 'Ninh Binh'),
(42, 'Phu Tho'),
(43, 'Ninh Thuan'),
(44, 'Phu Yen'),
(45, 'Ha Nam'),
(46, 'Ha Tinh'),
(47, 'Dong Thap'),
(48, 'Soc Trang'),
(49, 'Kon Tum'),
(50, 'Quang Binh'),
(51, 'Quang Tri'),
(52, 'Tra Vinh'),
(53, 'Hau Giang'),
(54, 'Son La'),
(55, 'Bac Lieu'),
(56, 'Yen Bai'),
(57, 'Tuyen Quang'),
(58, 'Dien Bien'),
(59, 'Lai Chau'),
(60, 'Lang Son'),
(61, 'Ha Giang'),
(62, 'Bac Kan'),
(63, 'Cao Bang');

/* ROUTE TABLE*/
CREATE TABLE route (
    bus_id int primary key,
    seat_type varchar(50),
    start_station int,
    end_station int,
    number_of_seat int,
    start_time time,
    arrive_time time,
    price decimal(10,0)
);

/*DATE_ROUTE TABLE*/
create table date_route(
	date_id date,
    date_from int,
    date_to int,
    date_bus int,
    date_available int
);

/*TICKET TABLE*/
create table tickets(
	ticket_id varchar(100) primary key,
    user_id int,
    ticket_phone varchar(20),
    date_id date,
    bus_id int
); 

