// PrimTypeAST.java
// This file was generated by AST2J (0.5a) on Mon Jul 24 17:09:48 JST 2017
// Source: ast2j.ast Version: 1.0 Author: Takuo Watanabe (takuo@acm.org)
package jp.ac.titech.c.psg.ast2j;
class PrimTypeAST extends TypeAST {
    private String name;
    public PrimTypeAST (String name) {
        this.name = name;
    }
    public String getName () { return name; }
    public void accept (ASTVisitor v) {
        v.visitPrimTypeAST(this);
    }
}
