USE `fogcarport`;

INSERT INTO `components` (`description`, `help_text`, `length`, `width`, `height`, `price`) VALUES
('38x57mm T1 Lægte Stemplet og godkendt til tag', 'Max afstand 32cm.', 6600, 38, 57, 100.00),		#id1
('Taglægte 38x57mm DK18', 'Gulvstøver pr 60 cm', 2300, 38, 57, 50.00),								#id2
('Reglar 50x100mm', 'Løsholter til redskabsrum', 2500, 50, 100, 70.00),								#id3
('Husmandsgul 21x110mm', 'Gulv i redskabsrum', 2100, 110, 21, 100.00);								#id4

INSERT INTO `bills_of_material` (`bill_id`, `component_id`, `amount`) VALUES
(1, 2, 2),		#id1
(1, 3, 2),
(1, 4, 1),
(2, 1, 3),		#id2
(2, 4, 2);

INSERT INTO `customers` (`name`,`email_address`, `password`, `phone_number`) VALUES
('bittie_bertha', 'bertha@testmail.com', '1234', 26154895);			#id1

INSERT INTO `employees` (`name`, `email_address`, `password`, `phone_number`, `rank`) VALUES
('halltheprotocol', 'hall@testmail.com', '4567', 36459865, 'admin'),				#id1
('barefooted_brandan', 'brandan@testmail.com', '7891', 12127845, 'storeworker'),	#id2
('SuperAdministrator', 'admin@fog.dk', '1337', 37373737, 'superadmin');				#id3

INSERT INTO `roof_type` (`type`, `color`, `version`) VALUES
('Betontagsten', 'Teglrød', NULL),		#id1
('Betontagsten', 'Sort', NULL),			#id2
('Eternittag', 'Grå', 'B6'),			#id3
('Eternittag', 'Mokka (brun)', 'B7');	#id4

INSERT INTO `carport` (`roof_type_id`, `carport_length`, `carport_width`,
			`carport_height`, `has_shed`, `shed_length`, `shed_width`, `shed_height`) VALUES
(1, 4000, 2500, 2000, 'no', NULL, NULL, NULL),		#id1
(2, 3500, 2000, 2000, 'yes', 1000, 1000, 1500);		#id2

INSERT INTO `orders` (`carport_id`, `customer_id`, `bill_id`, `customer_address`,
			`order_receive_date`, `order_status`, `order_send_date`) VALUES
(1, 1, 1, 'fantasivej 12 Lyngby', '2019-04-03', 'sent', '2019-04-14'),		#id1
(2, 1, 2, 'fantasivej 12 Lyngby', '2019-04-25', 'pending', NULL);			#id2

INSERT INTO `cases` (`order_id`, `customer_id`, `employee_id`, `case_status`) VALUES
(1, 1, 2, 'closed'),
(2, 1, 3, 'open');