# AndGitRepos
An android mobile application which lists github android repositories using [Github REST API v3](https://developer.github.com/v3/search/)

It's a simple application fetches android repositories which have more stars and updated not more than 2 days ago.

### Architecture
Developed using android architecture components

### Screen 1
- Loads the data using retrofit 2. Shows the title, description, language, star & fork count for each repository.

### Screen 2
- One can reach this screen by tapping one of the repos listed in screen 1
- Detail view of the repository can be seen here. Like, homepage url, topics, watch count, number of open issues and contributors and more.

<img src="../master/screenshots/device-2018-03-02-013719.png" width="300"> <img src="../master/screenshots/device-2018-03-02-013757.png" width="300">

### Dependencies
- [Kotlin](https://developer.android.com/kotlin/index.html)
- [RxJava 2](https://github.com/ReactiveX/RxJava)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
- [Glide v4](https://bumptech.github.io/glide/)
- [Retrofit 2](http://square.github.io/retrofit/)
- [Dagger 2](https://github.com/google/dagger)

### Caution
- Some of the github APIs are still under development(as of 02/03/2018 02:21 AM) like to [render readme as html](https://github.com/google/go-github/issues/727) and [read topics](https://developer.github.com/v3/repos/#list-all-topics-for-a-repository) are used in the developement.

## Authors

* [**Sundeep1501**](https://github.com/Sundeep1501)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
