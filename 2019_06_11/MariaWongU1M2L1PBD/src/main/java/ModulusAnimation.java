/**
 * This app draws an ASCII-based animation.
 * Displays animation using a for loop and modulus to determine which frame to show.
 * @params args
 *
 */

public class ModulusAnimation {

    public static void main(String[] args) throws Exception {

        for ( int i=0; i<80; i++ )
        {
            if ( i%20 == 0 )
                System.out.print("                    <<<<<<<   \r");
            else if ( i%20 == 1 )
                System.out.print("                  <<<<<<<<    \r");
            else if ( i%20 == 2 )
                System.out.print("                <<<<<<<<      \r");
            else if ( i%20 == 3 )
                System.out.print("              <<<<<<<<        \r");
            else if ( i%20 == 4 )
                System.out.print("            <<<<<<<<          \r");
            else if ( i%20 == 5 )
                System.out.print("          <<<<<<<<            \r");
            else if ( i%20 == 6 )
                System.out.print("        <<<<<<<<              \r");
            else if ( i%20 == 7 )
                System.out.print("      <<<<<<<<                \r");
            else if ( i%20 == 8 )
                System.out.print("    <<<<<<<                   \r");
            else if ( i%20 == 9 )
                System.out.print("  <<<<<<<<                    \r");
            else if ( i%20 == 10 )
                System.out.print("<<<<<<<<                      \r");
            else if ( i%20 == 11 )
                System.out.print("  >>>>>>>>                    \r");
            else if ( i%20 == 12 )
                System.out.print("    >>>>>>>>                  \r");
            else if ( i%20 == 13 )
                System.out.print("      >>>>>>>>                \r");
            else if ( i%20 == 14 )
                System.out.print("        >>>>>>>>              \r");
            else if ( i%20 == 15 )
                System.out.print("          >>>>>>>>            \r");
            else if ( i%20 == 16 )
                System.out.print("            >>>>>>>>          \r");
            else if ( i%20 == 17 )
                System.out.print("              >>>>>>>>        \r");
            else if ( i%20 == 18 )
                System.out.print("                >>>>>>>>      \r");
            else if ( i%20 == 19 )
                System.out.print("                  >>>>>>>>    \r");

            Thread.sleep(200);
        }

    }

}
