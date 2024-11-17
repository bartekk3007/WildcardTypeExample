import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            // Uzyskanie pola listWildcard z klasy MyClass
            Field listWildcardField = MyClass.class.getDeclaredField("listWildcard");
            analyzeField(listWildcardField);

            // Uzyskanie pola listExtends z klasy MyClass
            Field listExtendsField = MyClass.class.getDeclaredField("listExtends");
            analyzeField(listExtendsField);

            // Uzyskanie pola listSuper z klasy MyClass
            Field listSuperField = MyClass.class.getDeclaredField("listSuper");
            analyzeField(listSuperField);

        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
    }
    public static void analyzeField(Field field)
    {
        Type fieldType = field.getGenericType();
        if (fieldType instanceof ParameterizedType)
        {
            ParameterizedType parameterizedType = (ParameterizedType) fieldType;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            for (Type typeArgument : typeArguments)
            {
                if (typeArgument instanceof WildcardType)
                {
                    WildcardType wildcardType = (WildcardType) typeArgument;
                    System.out.println("Pole: " + field.getName());
                    System.out.println("Górne ograniczenie: ");
                    for (Type upperBound : wildcardType.getUpperBounds())
                    {
                        System.out.println("\t" + upperBound.getTypeName());
                    }
                    System.out.println("Dolne ograniczenie: ");
                    for (Type lowerBound : wildcardType.getLowerBounds())
                    {
                        System.out.println("\t" + lowerBound.getTypeName());
                    }
                }
            }
        }
    }
}