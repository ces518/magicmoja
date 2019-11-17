package me.june;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 17/11/2019
 * Time: 3:13 오후
 **/
@Target(ElementType.TYPE) // TYPE 으로 지정하면, 인터페이스 ,클래스, enum에 지정이 가능함.
@Retention(RetentionPolicy.SOURCE) // 소스레벨에서 만 유지하고, 컴파일시 애노테이션 프로세서로 소스코드를 생성할것임
public @interface Magic {
}
