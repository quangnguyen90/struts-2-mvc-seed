package common.base;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;


/**
 * 業務ロジックを定義するクラスの基底クラスです。<br>
 * 各業務ロジッククラスはこのクラスを継承して作成して下さい。<br>
 *
 * @author
 * @version $Id: DaoConfig.java 2 2006-03-27 09:59:16Z susumu.araki $
*/
public abstract class DaoConfig {

	protected static Logger log = Logger.getLogger(DaoConfig.class);

	protected static SqlSessionFactory sqlSessionFactory;

	static {
		try {

		String resource = "mybatis-config.xml";
		InputStream reader = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


		} catch (Exception e) {
			e.printStackTrace();
			log.fatal(e,e);
//		throw new SystemException("Error initializing MyAppSqlConfig class. Cause: " + e);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		DaoConfig.sqlSessionFactory = sqlSessionFactory;
	}







}
