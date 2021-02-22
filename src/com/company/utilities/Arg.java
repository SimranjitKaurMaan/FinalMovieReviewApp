package com.company.utilities;

public class Arg
{
    /**
     * This method is called to determines whether the specified parameter is null.
     * @param parameter The parameter.
     * @param <T> The object type being validated.
     * @return A reference to the validated object.
     */
    public static <T> T isNotNull(T parameter)
    {
        if (parameter == null) {
            throw new NullPointerException("Parameter cannot be null");
        }

        return parameter;
    }


    /**
     * This method is called to determines whether the specified parameter is null or white space.
     * @param parameter The parameter.
     * @return  A reference to the validated string object.
     */
    public static String isNotNullOrWhiteSpace(String parameter)
    {
        if (isNotNull(parameter).isEmpty())
        {
            throw new IllegalArgumentException("The parameter cannot be empty or whitespace.");
        }

        return parameter;
    }
}
