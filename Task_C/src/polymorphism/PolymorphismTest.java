package polymorphism;

public class PolymorphismTest {

	public static void main(String[] args) {
		ABaseClass base = new ABaseClass();
		ASubClass sub = new ASubClass();
		ABaseClass subAsBase = new ASubClass();
		base.aMethod(sub);
		base.aMethod(subAsBase); // ** The runtime type of subAsBase does not matter here ... 
		base.aMethod(base);
		sub.aMethod(sub);
		sub.aMethod(subAsBase); // **
		sub.aMethod(base);
		subAsBase.aMethod(sub);		// ** but it matters here. Why?
		subAsBase.aMethod(subAsBase);
		subAsBase.aMethod(base);
		
	}

}

/*

1.
Explain why the actual runtime type of subAsBase is not taken
into account when invoking the aMethod method.
​
Try the other invocations that are deactivated currently and explain the results of
invoking a method called aMethod there

Answer:
"Because virtual function calls are bound simply by looking through the vtable provided by the first argument (the this object), 
so the runtime types of the other arguments are completely irrelevant."
The this object is ABaseClass when initialized as a ABaseClass, however:

"Virtual function calls are not bound until the time of invocation"

When the call is made, the type is ABaseClass, but when the the method toString is invoked,
the type is looked up by name at runtime. 

When base.aMethod(subAsBase) is called, the method taking argument of type of type ABaseClass is called,
but calling toString looks up the object's type.

When subAsBase.aMethod(sub) is called, the method taking argument of type ASubClass is instead called.
*/
