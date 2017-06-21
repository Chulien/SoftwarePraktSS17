package first;

import java.util.concurrent.Callable;
import java.util.function.Function;

import io.reactivex.*;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class HellWorld {

	public static void main(String[] args){
		
		/*
		Observable.range(1, 5).subscribe(
				number -> System.out.println(number),
				error -> System.out.println("error"),
				() -> System.out.println("completed"));
		*/
		
		//Observable.fromCallable(thatReturnsNumberOne()).map(numberToString(
		
	}
	
	private Callable<Integer> thatReturnsNumberOne(){
		return()->{
			System.out.println("Observable Thread: "+Thread.currentThread().getName());
			return 1;
		};
	}
	
	private Function<Integer, String> numberToString(){
		return number->{
			System.out.println("Operator Thread: "+Thread.currentThread().getName());
			return String.valueOf(number);
		};
	}
	
	private Consumer<String> printResult(){
		return result->{
			System.out.println("Subscriber Thread: "+Thread.currentThread().getName());
			System.out.println("Result: "+result);
		};
	}
}
