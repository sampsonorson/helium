This project uses 'android' and 'helium' plugins for gradle.

In [`SampleActivity`](https://github.com/stanfy/helium/blob/master/samples/android/src/main/java/com/stanfy/helium/sample/android/SampleActivity.java#L8)
you'll find imports of classes which sources are not located in `src/main/java`.
Theses classes are generated by Helium from the
[specification file](https://github.com/stanfy/helium/blob/master/samples/android/src/api/twitter.api).

Generation options are defined in [`build.gradle` file](https://github.com/stanfy/helium/blob/master/samples/android/build.gradle#L46).

The project can be easily imported to Android Studio, and generated classes will be accessible in your editor.

Helium also adds an additional task for running REST API tests generated from the spec.
To launch these tests run
```
  gradle runApiTests
```

After running gradle build you should see a message about failed test.
If you open the tests report file, you'll see that there are complains about
unexpected field `id` for `User` objects in received response.
This is an example how Helium validates received data structures, and tests can be easily
fixed with uncommenting [line that described the field in the spec](https://github.com/stanfy/helium/blob/master/samples/android/src/api/twitter.api#L16).