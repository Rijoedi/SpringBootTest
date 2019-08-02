package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//Repositoryクラス。
//Repositoryクラスは、DBとの直接やりとりするところ。
//@Repositoryを付与するとDIコンテナに登録される。
@Repository
public class HelloRepository {
	
	//JdbcTemplateを扱うためには「@Autowired」が必要。
	//JdbcはSpringが提供しているJDBC用のクラス。
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//Map型(結果：{employee_id=1, employee_name=sugie, age=23})を返すfindOneメソッド。
	//idを引数にして、そのidである従業員をDBからSELECTする。
	public Map<String, Object> findOne(int id){
	
	//DBで使うSQL文。引数で受け取った（UIで入力された「従業員番号」=id　と一致するユーザーをDBから検索する)
	String query = "SELECT employee_id, employee_name, age FROM employee WHERE employee_id=?"; //*1
	
	//Mapに、jdbcTemplateを使って検索結果を格納する。
	//「検索結果：	{employee_id=1, employee_name=sugie, age=23}」
	//JdbcTemplate.queryForMapを使うと検索結果を自動でMap型で返してくれる。
	Map<String, Object> employee = jdbcTemplate.queryForMap(query,id);
	
	System.out.println(employee);
	
	return employee;
	//{employee_id=1, employee_name=sugie, age=23}を返す
	//「HelloController」で呼び出され、「Employeeクラス」のインスタンスとなる。
	}
}

//*1 : 	String query = "SELECT "+"employee_id, "+"employee_name, "+"age "+"FROM "+"employee "+"WHERE" +"employee_id=?";　だとエラー。なぜ？