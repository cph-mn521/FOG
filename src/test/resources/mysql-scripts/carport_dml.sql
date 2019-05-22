USE `fogcarport_TEST`;
SET FOREIGN_KEY_CHECKS=0;

INSERT INTO `components` (`description`, `help_text`, `length`, `width`, `height`, `price`) VALUES
('38x73 mm. taglægte T1', 'til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte', 5400, 38, 73, 91.53),
('Taglægte 38x57mm DK18', 'Gulvstøver pr 60 cm', 2300, 38, 57, 50.00),
('Reglar 50x100mm', 'Løsholter til redskabsrum', 2500, 50, 100, 70.00),
('Husmandsgul 21x110mm', 'Gulv i redskabsrum', 2100, 110, 21, 100.00),
('Cembrit tagskrue sortblå 6x100 400stk','Tagskrue til fastgørelse af Cembrit eternit tagplader',100,6,0,184.80),
('Cembrit tagskrue sortblå 120mm m/skive','Cembrit sortblå tagskrue med skrive, til montering af B6, B7 og B9 bølgeplader',120,6,0,579.00),
('Cembrit B6S FK sortblå bølgeplade','Til øverste tagbeklædning. Af fibercement med indstøbte strips. Min. 14° taghældning',1090,1180,0,119.00),
('Røde vingetagsten','Gl. dansk forbrug: 14,6 stk/m2 - 6 stk/bdt - 144 stk/½pal. - 288 pr pal. lægteafstand: 325mm dækbredde 201',404,236,0,17.60),
('97x97	mm. trykimp. Stolpe', 'Til montering på spær', 3000, 97, 97, 144),
('45x195 spærtræ ubh.', 'Remme i sider, sadles ned i stolper Carport del', 4800, 45, 195, 131.85),
('bræddebolt 10 x 120 mm.', 'Til montering af rem på stolper', 120, 10, 10, 11.16),
('firkantskiver 40x40x11mm', 'Til montering af rem på stolper', 40, 40, 11, 5.78),
('Universalbeslag 190 mm højre', 'Til montering af spær på rem', 0, 0, 190, 7.95),
('Universalbeslag 190 mm venstre', 'Til montering af spær på rem', 0, 0, 190, 7.95),
('5,0 x 40 mm. beslagskruer 250 stk.', 'Til montering af universalbeslag + toplægte', 40, 0, 0, 210.00);					

INSERT INTO `bills_of_materials` (`order_id`, `component_id`, `amount`) VALUES
(1, 2, 2),		
(1, 3, 2),
(1, 4, 1),
(2, 1, 3),		
(2, 4, 2);

INSERT INTO `customers` (`name`,`email`, `password`, `phone_number`) VALUES
('bittie_bertha', 'bertha@testmail.com', '1234', '26154895'),	
('Børge Børgesen', 'boerge@boergemail.com', '123', '54789565');	

INSERT INTO `employees` (`name`, `email`, `password`, `phone_number`, `rank`) VALUES
('halltheprotocol', 'hall@testmail.com', '4567', '36459865', 'admin'),					
('barefooted_brandan', 'brandan@testmail.com', '7891', '12127845', 'storeworker'),		
('SuperAdministrator', 'admin@fog.dk', '1337', '37373737', 'superadmin');				

INSERT INTO `roof_types` (`type`, `color`, `slant`, `version`) VALUES
('Betontagsten', 'Teglrød', 25, NULL),		
('Betontagsten', 'Sort', 15, NULL),			
('Eternittag', 'Grå', 0, 'B6'),				
('Eternittag', 'Mokka (brun)', 0, 'B7');	

INSERT INTO `carports` (`order_id`, `roof_type_id`, `length`, `width`, `height`, `shed_length`, `shed_width`, `shed_height`) VALUES
(1, 1, 7000, 2500, 2000, 6000, 1500, 1000),				
(3, 2, 6000, 2000, 2000, 6000, 1500, 1000);				

INSERT INTO `orders` (`customer_id`, `customer_address`,
			`order_receive_date`, `order_status`, `order_send_date`, `total_price`) VALUES
(1, 'fantasivej 12 Lyngby', '2019-04-03', 'sent', '2019-04-14', 240.00),
(1, 'fantasivej 12 Lyngby', '2019-04-08', 'sent', '2019-04-14', 1245.00),
(1, 'fantasivej 12 Lyngby', '2019-04-09', 'sent', '2019-04-14', 1234.00),
(1, 'fantasivej 12 Lyngby', '2019-04-11', 'pending', NULL, 1231.00),
(1, 'fantasivej 12 Lyngby', '2019-04-12', 'pending', NULL, 1345.00),
(1, 'fantasivej 12 Lyngby', '2019-04-25', 'pending', NULL, 500);			

INSERT INTO `cases` (`order_id`, `customer_id`, `employee_id`, `case_status`,`case_type`,`msg_status`,`msg_owner`) VALUES
(1, 1, 2, 'closed','salesperson',"skip skoop jeg er lukket","uuuh, kan denne sag mon lukkes?"),
(2, 1, 4, 'open','salesperson',"skip skoop jeg er lukket","uuuh, kan denne sag mon lukkes?");

INSERT INTO `cases` (`order_id`, `customer_id`, `case_status`,`case_type`,`msg_status`,`msg_owner`) VALUES
(1, 1, 'open','salesperson',"skip skoop jeg er lukket","uuuh, kan denne sag mon lukkes?"),
(1, 2, 'open','salesperson',"kunden er retarderet","jeg ville gerne ha den nye vilde bil og en carport til"),
(1, 1, 'open','salesperson',"Ser fornuftigt ud n stuff","dette er en dummy case til salgs check");

INSERT INTO `cases` ( `employee_id`, `case_status`,`case_type`,`msg_status`,`msg_owner`) VALUES
(4, 'open','admin',"Ser fornuftigt ud n stuff","Jeg vil gerne ha fri på torsdag"),
(4, 'open','admin',"Ser fornuftigt ud n stuff","jeg syntes vi arbejder for meget");


INSERT INTO `fogcarport`.`messages` (`type`,messages.title ,`content`) VALUES
('storeworker',"Første Besked.","besked besked store"),
('salesperson',"Første Besked.","besked salg"),
('admin',"Første Besked.","besked admin"),
('superadmin',"Første Besked.","besked super"),
('all',"Første Besked.","besked til alle");

SET FOREIGN_KEY_CHECKS=1;
