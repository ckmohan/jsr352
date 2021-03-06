
## Getting Started

You must have node.js and its package manager (npm) installed.  
You can get them from [http://nodejs.org/](http://nodejs.org/).

### Install Dependencies

We have two kinds of dependencies in this project: tools and angular framework code.  The tools help
us manage and test the application.

* We get the tools we depend upon via `npm`, the [node package manager][npm].
* We get the angular code via `bower`, a [client-side code package manager][bower].

We have preconfigured `npm` to automatically run `bower` so we can simply do:

```
npm install
```

Behind the scenes this will also call `bower install`.  You should find that you have two new
folders in your project.

* `node_modules` - contains the npm packages for the tools we need
* `app/bower_components` - contains the angular framework files

*Note that the `bower_components` folder would normally be installed in the root folder but this location was changed
through the `.bowerrc` file.  Putting it in the app folder makes it easier to serve the files by a webserver.*

### Configure the Application
Users need to configure the URL for connecting the the backend JBeret REST API.  It can be done in one of the following
ways, in order of precedence:

* Pass it as gulp argument, for example, `gulp --restUrl http://localhost:8080/myapp/api/` 
* Configure it in `./config.json`
* Set environment variable, for example, 
    * In bash/sh/ksh: `export JBERET_REST_URL="http://localhost:8080/restAPI/api"`, or add to `~/.profile` so it's
    always available when opening a new terminal.
    * In csh/tcsh: `setenv JBERET_REST_URL "http://localhost:8080/restAPI/api"`, or add to `~/.cshrc` or `~/.tcshrc`
    so it's always available when opening a new terminal.
    * In Windows: `set JBERET_REST_URL="http://localhost:8080/restAPI/api"`, or add to Control Panel environment variable
     so it's always available when opening a new terminal.
* If all the above fails, the default value `/api` is used.

The javascript file `./common/batchRestService.js` contains a replacement token for the REST API URL, which is substituted
with real value during gulp build process.  The final javascript is in `./dist/bundle.js`.

### Build, Assembly and Run the Application

Running command `gulp`, or `gulp serve`, or `npm start` in project root directory will serve the app, 
watch any updates and automatically refresh browser.  The browser will automatically open at `http://localhost:3000`.

gulp tasks include:

* `default`: default to start task
* `serve`: build, watch, and start `browser-sync` server
* `serve-only`: start `browser-sync` server without `build` step
* `build`: build the app, including steps like lint, js, css, html, font, img
* `lint`: check quality of js and css
* `jshint`: lint task for js
* `csslint`: lint task for css
* `js`: combine all javascript files including all transitive dependencies, minify and bundle into `dist/bundle.js`
* `css`: combine all css files, minify and bundle into `dist/css/bundle.css`
* `img`: optimize all image files and place them in `dist/img`
* `html`: copy all html files into appropriate directories under `dist/`
* `font`: copy all required font files into appropriate directories under `dist/`
* `clean`: delete `dist/`

## Testing

There are two kinds of tests: Unit tests and End to End tests.

### Running Unit Tests

Unit tests are written in [Jasmine][jasmine], which we run with the [Karma Test Runner][karma]. 
We provide a Karma configuration file to run them.

* the configuration is found at `karma.conf.js`
* the unit tests are found next to the code they are testing and are named as `..._test.js`.

The easiest way to run the unit tests is to use the supplied npm script:

```
npm test
```

This script will start the Karma test runner to execute the unit tests. Moreover, Karma will sit and
watch the source and test files for changes and then re-run the tests whenever any of them change.
This is the recommended strategy; if your unit tests are being run every time you save a file then
you receive instant feedback on any changes that break the expected code functionality.

You can also ask Karma to do a single run of the tests and then exit.  This is useful if you want to
check that a particular version of the code is operating as expected.  The project contains a
predefined script to do this:

```
npm run test-single-run
```


### End to end testing

End-to-end tests are also written in [Jasmine][jasmine]. These tests are run with the [Protractor][protractor] 
End-to-End test runner.  It uses native events and has special features for Angular applications.

* the configuration is found at `e2e-tests/protractor-conf.js`
* the end-to-end tests are found in `e2e-tests/scenarios.js`

Protractor simulates interaction with our web app and verifies that the application responds
correctly. Therefore, our web server needs to be serving up the application, so that Protractor
can interact with it.

```
npm start
```

In addition, since Protractor is built upon WebDriver we need to install this.  This project comes with a predefined 
script to do this:

```
npm run update-webdriver
```

This will download and install the latest version of the stand-alone WebDriver tool.

Once you have ensured that the development web server hosting our application is up and running
and WebDriver is updated, you can run the end-to-end tests using the supplied npm script:

```
npm run protractor
```

This script will execute the end-to-end tests against the application being hosted on the
development server.


## Updating Angular

The angular framework library code and tools are acquired through package managers (npm and
bower) you can use these tools instead to update the dependencies.

You can update the tool dependencies by running:

```
npm update
```

This will find the latest versions that match the version ranges specified in the `package.json` file.

You can update the Angular dependencies by running:

```
bower update
```

This will find the latest versions that match the version ranges specified in the `bower.json` file.


## Loading Angular Asynchronously

This project supports loading the framework and application scripts asynchronously.  The
special `index-async.html` is designed to support this style of loading.  For it to work you must
inject a piece of Angular JavaScript into the HTML page.  The project has a predefined script to help
do this.

```
npm run update-index-async
```

This will copy the contents of the `angular-loader.js` library file into the `index-async.html` page.
You can run this every time you update the version of Angular that you are using.


## Serving the Application Files

While angular is client-side-only technology and it's possible to create angular webapps that
don't require a backend server at all, we recommend serving the project files using a local
webserver during development to avoid issues with security restrictions (sandbox) in browsers. The
sandbox implementation varies between browsers, but quite often prevents things like cookies, xhr,
etc to function properly when an html page is opened via `file://` scheme instead of `http://`.


### Running the App during Development

This project uses `browser-sync` to serve the app, watch any updates and automatically refresh browser. 
Running `gulp` with default task, or `gulp serve`task will start it.  `gulp serve` task builds the whole app
and assemble it under `/dist` directory.

Note that this command will block the terminal.  To stop `browser-sync`, just press `Ctrl-C`.

```
/Users/cfang/dev/jsr352/jberet-ui > gulp
```

`npm start` command is configured to just call `gulp`, so `npm start` is equivalent to `gulp` command.

This project also comes preconfigured with a local development webserver.  It is a node.js
tool called [http-server][http-server].  You can start this webserver with `npm start` but you may choose to
install the tool globally:

```
sudo npm install -g http-server
```

Then you can start your own development web server to serve static files from a folder by
running:

```
http-server -a localhost -p 8000
```

Alternatively, you can choose to configure your own webserver, such as apache or nginx. Just
configure your server to serve the files under the `dist/` directory.


### Running the App in Production

This really depends on how complex your app is and the overall infrastructure of your system, but
the general rule is that all you need in production are all the files under the `dist/` directory.
Everything else should be omitted.

Angular apps are really just a bunch of static html, css and js files that just need to be hosted
somewhere they can be accessed by browsers.

If your Angular app is talking to the backend server via xhr or other means, you need to figure
out what is the best way to host the static files to comply with the same origin policy if
applicable. Usually this is done by hosting the files by the backend server or through
reverse-proxying the backend server(s) and webserver(s).

### Screenshots

[All Screenshots Unordered](https://github.com/jberet/jsr352/issues/71)

[Recent Jobs List](https://cloud.githubusercontent.com/assets/2079251/13342502/2e5e7c82-dc10-11e5-8711-5d2db7be506b.png)

[Job Started](https://cloud.githubusercontent.com/assets/2079251/13355944/e1e88828-dc70-11e5-8c67-d86ae7d1a118.png)

[Choose Job Instances Criteria](https://cloud.githubusercontent.com/assets/2079251/13342505/2e644e82-dc10-11e5-94f4-5ca4055d707c.png)

[Choose Job Executions Criteria](https://cloud.githubusercontent.com/assets/2079251/13342504/2e640d46-dc10-11e5-80d9-02d8634c9290.png)

[Restart a Failed Job Execution](https://cloud.githubusercontent.com/assets/2079251/13342500/2e5de2e0-dc10-11e5-99fd-7864fa15827f.png)

[Abandon a Job Execution](https://cloud.githubusercontent.com/assets/2079251/13342498/2e5bd234-dc10-11e5-9355-ccb80be9d019.png)

[Job Execution Details](https://cloud.githubusercontent.com/assets/2079251/13342501/2e5e02a2-dc10-11e5-9c39-d7051da86621.png)

[Filter Job Executions](https://cloud.githubusercontent.com/assets/2079251/13342503/2e5e848e-dc10-11e5-8689-618d83d6b559.png)

[Export Job Execution Data](https://cloud.githubusercontent.com/assets/2079251/13342804/49114b42-dc13-11e5-896f-c08ead1a5d8a.png)

[Step Metrics Panel Collapsed](https://cloud.githubusercontent.com/assets/2079251/13342499/2e5caa1a-dc10-11e5-9dcc-c98792967847.png)

[Step Execution with Metrics Chart](https://cloud.githubusercontent.com/assets/2079251/13342506/2e6754e2-dc10-11e5-8666-19842e6d5c3a.png)

[Schedule a One-time Job Execution](https://cloud.githubusercontent.com/assets/2079251/14944314/6edd6e40-0fbd-11e6-936b-370b53669c05.png)

[Schedule a Repeating Job Execution](https://cloud.githubusercontent.com/assets/2079251/14944316/6ede6638-0fbd-11e6-923a-ab843ef23efc.png)

[Schedule a Calendar-based Job Execution](https://cloud.githubusercontent.com/assets/2079251/14944318/6edf9558-0fbd-11e6-95f2-efaf97e8706a.png)

[List All Batch Job Schedules](https://cloud.githubusercontent.com/assets/2079251/14944319/6ee063c0-0fbd-11e6-90ba-5366ff550d8f.png)

[Schedule Restarting a Job Execution](https://cloud.githubusercontent.com/assets/2079251/14944321/6ee7c8b8-0fbd-11e6-9320-33eb03045cef.png)

[Cancel a Job Execution Schedule](https://cloud.githubusercontent.com/assets/2079251/14944322/6ee8323a-0fbd-11e6-8299-df19bd308d06.png)