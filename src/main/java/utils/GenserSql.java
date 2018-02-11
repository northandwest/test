package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class GenserSql {

	public static void main(String[] args) {

		String sql = "ALTER TABLE `active_comment_record_{index}`	DROP INDEX `jar_create_time_index`,	ADD INDEX `idx_create_time_index` (`create_time`) USING BTREE,	DROP INDEX `jar_active_id_index`,	ADD INDEX `idx_active_id_index` (`active_id`)";
		String sql2 = "	ALTER TABLE `user_comment_{index}`	ADD COLUMN `showorder_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '鏅掑崟瀹℃牳鐘舵�' ,	ADD COLUMN `allow_showorder` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '鏅掑崟璧勬牸' ,	ADD COLUMN `order_delete` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '璁㈠崟鍒犻櫎鐘舵�' ";
		
		StringBuffer t1 = new StringBuffer();
		StringBuffer t2 = new StringBuffer();
		
		for(int i = 0; i < 256; i ++)
		{
			t1.append(sql.replace("{index}", String.valueOf(i))).append(";").append("\r\n");
//			t2.append(sql2.replace("{index}", String.valueOf(i))).append(";").append("\r\n");
		}
		
		try {
			FileUtils.write(new File("d://temp//sql//reward-index.sql"), t1.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			FileUtils.write(new File("d://temp//sql//comment_change.sql"), t2.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

}
