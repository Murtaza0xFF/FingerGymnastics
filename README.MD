
##Screenshot


<img src="http://i.imgur.com/KMV36hf.png" width="200"/> <img src="http://i.imgur.com/A72Nro0.png" width="200"/>

##Summary

Finger Tap consists of 3 different layers to it's structure; models, views, and presenters.
The presenter is a mediator for communication between the model, and the views; although, due to
Selection class being tightly tied to the MainActivity, the lack of network interaction, and the
lack of database, communication between view and the presenter is predominantly a one way traffic
in the direction of the presenter. The presenter is totally independent of the Android framework,
for better testability and polymorphism -- just in case another level is to show up.

The project uses Dagger 2 for injecting dependencies into the classes, and for scoping objects to either the
application scope (singleton), or the activity scope. This is too small a project for scoping to
have a significant impact, but if a database layer or a network layer were to show up, scoping would
come in handy to preserve data by decoupling the data layer and using it as a singleton,
or to make network calls only when appropriate. But for now, scoping is used just to avoid
repeated creation of expensive objects.

##Potential Improvements

- Use GridView over GridLayout for the added luxury of getting the position of the cell
using the coordinates.

- Integrate Espresso for testing the toggling of views by simulating the clicking.

- Use RxBinding to convert view events to observables.

## License

       MIT License

       Copyright (c) 2017 Murtaza Akbari

       Permission is hereby granted, free of charge, to any person obtaining a copy
       of this software and associated documentation files (the "Software"), to deal
       in the Software without restriction, including without limitation the rights
       to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
       copies of the Software, and to permit persons to whom the Software is
       furnished to do so, subject to the following conditions:

       The above copyright notice and this permission notice shall be included in all
       copies or substantial portions of the Software.

       THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
       IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
       FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
       AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
       LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
       OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
       SOFTWARE.
