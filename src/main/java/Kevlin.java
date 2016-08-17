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

    private static void tests()
    {
        assert format(
            list(
                list(n("class"), n("Foo")),
                block("{", "}", list())
            )
        ).equals("class Foo\n{\n}\n");
    }

    private static String format(AstNode node)
    {
        return "";
    }

    private static class AstNode
    {
        public final String contents;

        public AstNode(String contents)
        {
            this.contents = contents;
        }
    }

    private static AstNode n(String contents)
    {
        return new AstNode(contents);
    }

    private static AstNode list(AstNode... items)
    {
        return new AstNode("bar");
    }

    private static AstNode block(String start, String end, AstNode... items)
    {
        return new AstNode("foo");
    }
}
