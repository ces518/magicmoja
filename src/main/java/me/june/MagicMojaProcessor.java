package me.june;

import com.sun.source.tree.Tree;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 17/11/2019
 * Time: 3:15 오후
 **/

/**
 * Process 인터페이스를 구현해도 되지만, 자바에서 제공하는 AbstractProcessor 추상클래스를 구현해도 된다.
 * Process 에서 구현해야하는 여러 메서드들을 구현해주고 있다.
 */
public class MagicMojaProcessor extends AbstractProcessor {

    /**
     * 이 프로세서가 어떤 애노테이션들을 처리할 것인지 설정하는 메서드 오버라이딩.
     *
     * Element란 ?
     * - 패키지
     * - 클래스
     * - 메서드
     * 소스코드의 구성요소를 엘리먼트라고 부른다
     * 각 엘리먼트들이 프로세스를 할때 참조할 수 있다.
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Magic.class.getName());
    }

    /**
     * 몇 버전의 소스코드를 지원하는지 설정.
     * - 최신버전 지원하도록 설정
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 애노테이션 프로세서는 라운드 라는 개념으로 처리를 한다.
     * - 여러 라운드에 거쳐 처리를 한다.
     * - 각 라운드마다 프로세서에게 특정 애노테이션을 가지고 있는 엘리먼트를 찾으면 처리를 요청한다.
     * - 처리된 결과가 다음 라운드에게 전달될 수 있다.
     * - Filter Chaining 과 어찌보면 비슷한 느낌이다.
     *
     * 만약 여기서 true를 리턴하면, 애노테이션 프로세서가 처리를 한것이다.
     * true를 리턴하면, 다른 프로세서가 이를 처리하지 않는다.
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        /* 적절한 위치에 애노테이션을 사용했는지 체크 */
        /* 애노테이션을 사용한 엘리먼트에 대한 정보를 가져올수 있다.*/
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Magic.class);
        for (Element element : elementsAnnotatedWith) {
            /* 애노테이션이 인터페이스 아닌곳에 사용했을 경우 */
            if (element.getKind() != ElementKind.INTERFACE) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@Magic 애노테이션은 Interface만 지원하는 애노테이션 입니다.");
            } else {
                // 로깅
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing " + element.getSimpleName());
            }
        }
        return true;
    }
}
