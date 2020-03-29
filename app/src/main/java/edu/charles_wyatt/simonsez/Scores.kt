package edu.charles_wyatt.simonsez

import java.lang.reflect.Type

data class Scores(var name: String, var score: Int, var id: Int) : Type { override fun toString(): String { return super.toString() } }