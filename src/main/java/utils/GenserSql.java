package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class GenserSql {

	public static void main(String[] args) {

		String sql = "ALTER TABLE `user_comment_{index}`	ADD COLUMN `showorder_dealt` TINYINT(4) NOT NULL COMMENT '晒单处理' AFTER `dealt`;";

		String sql2 = "	ALTER TABLE `user_comment_{index}`	ADD COLUMN `showorder_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '晒单审核状态' ,	ADD COLUMN `allow_showorder` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '晒单资格' ,	ADD COLUMN `order_delete` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '订单删除状态' ";
		
		StringBuffer t1 = new StringBuffer();
		StringBuffer t2 = new StringBuffer();
		
		for(int i = 0; i < 256; i ++)
		{
			t1.append(sql.replace("{index}", String.valueOf(i))).append(";").append("\r\n");
//			t2.append(sql2.replace("{index}", String.valueOf(i))).append(";").append("\r\n");
		}
		
		try {
			FileUtils.write(new File("d://temp//sql//user_comment_change2.sql"), t1.toString());
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
