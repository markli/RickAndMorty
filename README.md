# RickAndMorty

This demo uses [https://rickandmortyapi.com/](https://rickandmortyapi.com/) web API, a few simple RESTful interfaces, all requests are GET requests and go over https. All responses will return data in json.

This is a simple demo, so there are not many comments, the programming ideas are as follows:

1. Use `retrofit2` and `rxjava2` to communicate with the server in the background, and use its own functions to implement caching
2. Use `gson` and automatically generate `pojo` entity. Can be converted online: [http://www.jsonschema2pojo.org/](https://rickandmortyapi.com/])
3. Use `picasso` to display and load web images.
4. UI implementations are all using the components provided by the original android, `BottomNavigationView`, `FragmentList`, `RecyclerView` and so on. It can be generated automatically using the wizard.
5. Use the `MVP` architecture design pattern to implement `observer` and `subject` communication using the `observer` design pattern and the `subscriber` design pattern.

===

![demo](gif/rickandmorty3.gif)
