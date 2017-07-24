// FieldDefAST.java
// This file was generated by AST2J (0.5a) on Thu May 19 17:16:11 JST 2005
// Source: ast2j.ast Version: 1.0 Author: Takuo Watanabe (takuo@acm.org)
package jp.ac.titech.cs.psg.ast2j;
class FieldDefAST extends AST {
    private String var;
    private TypeAST type;
    public FieldDefAST (String var, TypeAST type) {
        this.var = var;
        this.type = type;
    }
    public String getVar () { return var; }
    public TypeAST getType () { return type; }
    public void accept (ASTVisitor v) {
        v.visitFieldDefAST(this);
    }
}