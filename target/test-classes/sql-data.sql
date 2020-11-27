INSERT INTO `test`.`customer` (`first_name`, `last_name`) VALUES ('jordan', 'harrison');

INSERT INTO `test`.`product` (`name`, `model`, `stock`, `price`) VALUES ('hoover', 'hkfoor','10','20');

INSERT INTO `test`.`orders` (`customer_id`, `order_date`, `total`) VALUES ( '1','2020-10-10', '0');

INSERT INTO `test`.`orders_items` (`order_id`, `item_id`, `quantity`,`total`) VALUES ('1', '1','20',200);
