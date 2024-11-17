import java.util.List;

public class MyClass
{
    List<?> listWildcard;
    List<? extends Number> listExtends;
    List<? super Integer> listSuper;
}