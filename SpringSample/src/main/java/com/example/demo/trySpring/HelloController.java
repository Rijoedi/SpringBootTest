package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Controllerクラスは、HTTP GETリクエストを受け取った時に処理をする

//ControllerアノテーションをつけるとDIコンテナを利用できるようになる
@Controller
public class HelloController {

	@Autowired
	private HelloService helloService;


	//@GetMapping("/hello")アノテーションを付与すると,
	//localhost:8080/hello」で呼び出された時に行われる処理を指定できる。
	//Getリクエストの時はメソッド名の頭を「get」にするのが慣習。
	@GetMapping("/hello")
	public String getHello() {

		//「hello.html」に移行する
		//.htmlは必要ない
		return "hello";
	}

	//「hello.html」からPOSTリクエストが来た時に行われる処理
	//「th:value」で送られてきた「text1」を引数にする。
	//その場合、受け取る引数(今回では「text1」に@RequestParamアノテーションを付与する
	//「Model」は多分保存領域的意味。保存領域に「sample」という名前で「hello.html」の「th:value=text1」で入力された値を保存する
	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1")String str, Model model) {
		//受け取った保存領域に、受け取った文字列を「sample」として保存する。
		model.addAttribute("sample",str);
		
		//helloResponseメソッドに画面遷移
		//保存領域に保存した「sample」と共に。
		return "helloResponse";
	}

	//「hello.html」で「従業員番号」を入力されて、「/hello/db」へPOSTリクエストが送られた場合に行う処理
	//目的は「入力された従業員番号」をもつデータをDBから引っ張ること
	@PostMapping("/hello/db")
	public String postDbRequest(@RequestParam("text2")String str, Model model) {

		//「hello.html」の「従業員番号」の欄で　入力された「text2」を受け取り、それをint型にする。
		int id = Integer.parseInt(str);

		//Employeeインスタンスを、helloServiceクラスのfindOneメソッドに引数idを渡してreturnで受ける。
		Employee employee = helloService.findOne(id);
		
		//検索結果をModelに登録
		model.addAttribute("id", employee.getEmployeeId());
		model.addAttribute("name", employee.getEmployeeName());
		model.addAttribute("age" , employee.getAge());
		
		//helloResponseDB.htmlに画面遷移
		return "helloResponseDB";
	}
}