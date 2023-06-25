package model.visitor

interface IElement {

    fun accept(visitor: IVisitor)
}