INSERT INTO`general_content`(`platform_id`,`title`,`created_date`)VALUE('tvos6','迷你血战2');


DELIMITER $$

USE `skyg_tvos6.1`$$

DROP PROCEDURE IF EXISTS `batchIinsertByYJN`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `batchIinsertByYJN`()
  BEGIN
    DECLARE num INT;
    SET num=1;
    WHILE num < 25 DO
      INSERT INTO `general_cell_content_relation`(`cell_id`, `content_id`) VALUES(168+num, 123+num);
      SET num=num+1;
    END WHILE;
  END$$

DELIMITER ;




