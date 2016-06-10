package com.sky
import org.apache.commons.lang.RandomStringUtils as RSU
def add = { x,y-> x+y }
def addAll = { ... values -> values.sum()}


println addAll(4,3,2)

println addAll(new Date(), 4)

println add(5,7)

def add2 = add.curry(4)

assert add2(12) == 16

def mult = { x,y -> x * y }
def times2 = mult.rcurry(5)

assert times2(4) == 20

def randomClosure = { size, letters, numbers ->
    RSU.random(size, letters, numbers)
}

def randomNumbers = randomClosure.rcurry(false, true)
def randomLetters = randomClosure.ncurry(1, true, false)

println randomClosure(10, true, true)
println randomNumbers(10)
println randomLetters(10)

println randomClosure.getMaximumNumberOfParameters()

def nums = [1,4,6,7,8 ]
println nums.inject { acc, val ->
    println "acc=$acc, val=$val"
    acc + val
}

List strings = 'this is a list of strings'.split()
List sorted = strings.sort(false)
println sorted

sorted.inject { prev, curr ->
    println "prev=$prev, curr=$curr"
    assert prev <= curr
    curr
}

List list = [1,4,6,7,8 ].asImmutable()

//functional programming

def add3 = { it + 3 }
def time2 = { it * 2 }

def times2add3 = add3 << time2
assert 13 == times2add3(5)




