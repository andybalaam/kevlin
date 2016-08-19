import java.util.ArrayList;
import java.util.List;

public class Kevlin
{
    // TODO:
    // * Line breaks around blocks always
    // * Possible line breaks in param lists, if conditions etc.
    // * Line breaks around 1-line blocks
    // * Re-ordering by type:
    //     main, static inner classes, inner classes, members, methods
    // * Re-ordering of items inside a type by name


    public static void main(String[] args)
    {
        tests();
    }

    private static void a(AstNode node, String expected)
    {
        String actual = format(node);
        if (!actual.equals(expected))
        {
            System.err.println("Assertion failed.  Actual then expected:");
            System.err.println(actual.replaceAll("\n", "\\\\n"));
            System.err.println(expected.replaceAll("\n", "\\\\n"));
            System.exit(1);
        }
    }

    private static void tests()
    {
        System.out.println("Running tests ...");
        Tests.Short_variable_declaration_on_one_line();
        Tests.Class_block_wraps_even_if_fits();
        System.out.println("All tests passed.");
    }

    private static class Tests
    {
        private static void Short_variable_declaration_on_one_line()
        {
            a(
                list(list(p("int"), p("x")), p("="), list(p("3"), n(";"))),
                "int x = 3;"
            );
        }

        private static void Class_block_wraps_even_if_fits()
        {
            a(
                list(
                    list(p("class"), p("Foo")),
                    block("{", "}", list())
                ),
                "class Foo\n{\n}\n"
            );
        }
    }

    private static class Appender
    {
        private final StringBuilder str = new StringBuilder();
        private boolean pad = false;

        public void add(AstNode node)
        {
            if (pad && node.pad)
            {
                str.append(" ");
            }
            if (node.contents != null)
            {
                str.append(node.contents);
                pad = node.pad;
            }
            if (node.start != null)
            {
                str.append("\n");
                str.append(node.start);
                str.append("\n");
            }
            for (AstNode child : node.children)
            {
                add(child);
            }
            if (node.end != null)
            {
                str.append(node.end);
                str.append("\n");
            }
        }

        public String formatted()
        {
            return str.toString();
        }
    }

    private static String format(AstNode node)
    {
        Appender ret = new Appender();
        ret.add(node);
        return ret.formatted();
    }

    private static class AstNode
    {
        public final String contents;
        public final boolean pad;
        public final String start;
        public final String end;
        public final AstNode[] children;

        public AstNode(String contents, boolean pad)
        {
            this.contents = contents;
            this.pad = pad;
            this.start = null;
            this.end = null;
            this.children = new AstNode[0];
        }

        public AstNode(String contents)
        {
            this.contents = contents;
            this.pad = false;
            this.start = null;
            this.end = null;
            this.children = new AstNode[0];
        }

        public AstNode(String start, String end, AstNode... children)
        {
            this.contents = null;
            this.pad = false;
            this.start = start;
            this.end = end;
            this.children = children;
        }

        public AstNode(AstNode... children)
        {
            this.contents = null;
            this.pad = false;
            this.start = null;
            this.end = null;
            this.children = children;
        }
    }

    private static AstNode p(String contents)
    {
        return new AstNode(contents, true);
    }

    private static AstNode n(String contents)
    {
        return new AstNode(contents);
    }

    private static AstNode list(AstNode... items)
    {
        return new AstNode(items);
    }

    private static AstNode block(String start, String end, AstNode... items)
    {
        return new AstNode(start, end, items);
    }
}
