# Phasher ✅

> So, around a week ago I was struggling to compare similarities between two images and found a way i.e Phash (perceptual hash) algorithm. It's based upon https://www.phash.org/.❤️

## ⌨️ Usage

![latestVersion](https://img.shields.io/github/v/release/KapilYadav-dev/Phasher)

### 1. Add dependency.
```groovy
repositories {
  maven { url 'https://jitpack.io' } // Add jitpack
}

dependencies {
  implementation 'com.github.KapilYadav-dev:Phasher:Tag'
}

```

### 2. Use Phash() function.
#### 1st way using file path.
```kotlin
fun Phash(
    fileOnePath: String?, // path of Image one 
    fileTwoPath: String?, // path of Image two
    bitSize: Int = 8  // This is the size you want to resize, can play with it for more accuracy and its best with the value 8
)
```
#### 2nd way using file uri.
```kotlin
fun Phash(
    uriOne: Uri?, // Uri for Image one
    uriTwo: Uri?, // Uri for Image two
    context: Context
)
```
#### Finally use ```getSimilarityScore()``` method which will return % of confidence.
#### i.e.
```kotlin
val score = Phash(path1,path2).getSimilarityScore()
// score = 70 %
val scoreTwo = Phash(uri1,uri2,context).getSimilarityScore()
// score = 70%
```

## Results
| Image 1 | Image 2 |
| :---: | :---: |
| <img src="/img1.jpeg" width="200"/> | <img src="/img2.jpeg" width="200" /> |
## Result 89 % match ✅

## Important Note ✍️
Choose your threasold for your image as per your usecase. <br>
For a standard one, we use 75 % threasold that means above its image are similar.
## ✍️ Author

👤 **mrkaydev**

* Linkedin: <a href="https://www.linkedin.com/in/mrkaydev/" target="_blank">@mrkaydev</a>
* Email: infokaydev@gmail.com

Feel free to ping me 😉

## 🤝 Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Open an issue first to discuss what you would like to change.
1. Fork the Project
1. Create your feature branch (`git checkout -b feature/amazing-feature`)
1. Commit your changes (`git commit -m 'Add some amazing feature'`)
1. Push to the branch (`git push origin feature/amazing-feature`)
1. Open a pull request

Please make sure to update tests as appropriate.

## ❤ Show your support

Give a ⭐️ if this project helped you!

<a href="https://www.buymeacoffee.com/mrkaydev" target="_blank">
    <img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" width="160">
</a>


## 📝 License

```
Copyright © 2022 - mrkaydev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
