# Pinterest-Clone

## Configuration
This project build and develop using 
- Android Studio 3.5
- Kotlin version 1.3.41
- Gradle 3.5.1

## Testing
### Using Android Studio
 - Navigate to app>java>com.shiburagi.pinterestclone(androidTest)>MainActivityTest
 - Right click on File
 - select "Run 'MainActivityTest'"

### Using terminal/ command line
  - Run and excute ```./gradlew connectedDebugAndroidTest```

## Test Case
- using two different url, which will call alternate (by using this equation **(length/10) % 2**) when user scroll and reach the end of the screen, 
- The image clickable, which open new page to view and enlarge.
- the image will read from cache, if the image already load
- the total image's height for left and right side should be at most one image's height
- Every image load independent, with different thread

## Preview
![Alt Text](https://github.com/shiburagi/Pinterest-Clone/blob/master/preview/ezgif.com-video-to-gif%20(1).gif?raw=true)

## Screenshot

<img src="https://github.com/shiburagi/Pinterest-Clone/blob/master/preview/Screenshot_1571563626.png?raw=true" width="300px" >

<img src="https://github.com/shiburagi/Pinterest-Clone/blob/master/preview/Screenshot_1571563633.png?raw=true" width="300px" >
