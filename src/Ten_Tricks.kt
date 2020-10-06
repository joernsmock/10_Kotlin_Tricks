/*
* trying to type this into the IDE, which makes a mess
* out of it. Maybe I learn something about the IDE this
* way (because the IDE seems to be unavoidable (but VIMable
* in kotlin/android
 */

// #1 explosive placeholders
class Test: Callable<String> {
    override fun call(): String {
        if (something()) {
                return doSomething()
            } else {
            TODO("doSomethingElse()?")
            }
    }
}
// #2 semantic validation
fun join(sep: String, strings: List<String>: String {
    require(sep.length < 2)  { "sep.length < 2 " + sep }
    // ...
}

//throws IllegalArgumentException
require(value: Boolean)
require(value: Boolean, lazyMessage: () -> Any)

requireNotNull(value: T?): T
requireNotNull(value: T?, lazyMessage: () -> Any): T

//throws IllegalStateException
check(value: Boolean)
check(value: Boolean, lazyMessage: () -> Any))

checkNotNull(value: T?): T
checkNotNull(value: T?, lazyMessage: () -> Any): T

//throws AssertionError
assert(value: Boolean)
assert(value: Boolean, lazyMessage: () -> Any))

assertNotNull(value: T?): T
assertNotNull(value: T?, lazyMessage: () -> Any): T

// #3 Anything and Nothing
/*
'nothing' descends from everything, so the compiler can do stuff???
 */

// #4 Let
var user: User? = null
user?.let {
    // it == user not null, only read once!
}

/*
theres other ones like this:
user?.apply
user?.run
user?.also
*/

// use function result multiple times without a variable for this:

someMethod().let {
    result ->
// use result multiple times
}

// #5 (nice) multiline string literals
val string = """foo
    bar
    baz""".trimIndent()
val string2 = """|foo
                 |bar
                 |baz""".trimMargin()

// #6 lazy but speedy
import kotlin.LazyThreadSafetyMode.NONE

class NamePrinter(val firstName: String, val lastName: String) {
    val fullName: String by lazy(NONE) { "$firstName $lastName" }

    /* the parameter to lazy() defines the locking strategy --
    sometimes you dont need 'big tools' (only one thread running sometimes

    ... when and where is what locked???

    There is a mode called 'publication' where it allows multiple
    threads to call the lambda and it will only use the first
    person [garbled] return
     */
    fun printName() {
        println(fullName)
    }
}
// #7 code block measurement
val helloTook = measureTimeMillis {
println("Hello, world!")
}
println("Saying 'hello' took ${helloTook}ms")

// #8 deprecation levels

import kotlin.DeprecationLevel.ERROR

/*  there is a level which can give you (sort of) steps
    of deprecation.

    level = ERROR
    level = HIDDEN

    use of this: binary compatibility. you can have a thing
    in the bytecode but prevent users from using it.
    */

// leave out the ', level = ...' and dont import and get
// just a wiggle in the IDE

@Deprecated("Use strings.joinToString(sep).", level = ERROR)
fun join(sep: String, strings: List<String>): String {
    //...
}
join(",", list("me", "you"))

// #9 deprecation replacements
@Deprecated("Use Guava's Joiner.",
    replaceWith = ReplaceWith("Joiner.on(sep).join(strings)",
        imports = "com.google.common.base.Joiner"))
    fun join(sep: String, strings: List<String>: String {
        // ...
    }
join(", ", listOf("me, "you""))

// #10 erasing erasure

/* change function names in the class file only, but not
in the source file

(you cannot have the next two otherwise, because they both
have 'List' as parameters and that does not work with the
same function name)
[because the compiler has superior generic inference (???) so
the compiler can figure out which function to call]
 */

@JvmName("sortStrings")
fun sort(strings: List<String>) {
    // ...
}
@JvmName("sortInts")
fun sort(ints: List<Int>) {
    // ...
}
