# carols-app-prototype

Prototype of Carol's app, to begin to prove some concepts and gather data.

* [React frontend](https://bitbucket.org/oliverhulett/cap-frontend/src/master/)
* [Java backend](https://bitbucket.org/oliverhulett/cap-backend/src/master/)
* Running on Heroku.

## Rules of Engagement

Raise an issue using the attached Trello board, discuss it and reach an implementation consensus.
Move the issue card into the "In Progress" column, branch, and code.
Ensure you code changes have sufficient test coverage before raising a PR.
Once approved, merge your PR.
You can release any green master build by clicking the release step.

## Building, Testing, and Running Locally

1. Clone the repo and change into the repository root directory.
1. Ensure you have the right version of `node` installed by running `nvm use`.

On Mac OS `brew install watchman` may be required to run Jest tests successfully. See https://github.com/facebook/create-react-app/issues/4540

### Building and Testing the Frontend

1. Run `yarn install`.
1. Run the tests with `yarn test`.

### Running the Frontend Locally

1.  Clone the repo, and run `yarn install`.
1.  Run the application using `yarn start`.

Then you should be able to use the service at `localhost:3000`

## Deploying

Deployments can be made by running the release step on any green master build in Bitbucket pipelines.

## Design/Outline

### Search Results
```
####################################################################################################
#########
#########                    ################################## Search
#########
#########          Product Name                                            #####
#########              Manufacturer/Provider                               #####
#########              Overall Rating                                      #####
#########
#########          Product Name                                            #####
#########              Manufacturer/Provider                               #####
#########              Overall Rating                                      #####
#########
#########          Product Name                                            #####
#########              Manufacturer/Provider                               #####
#########              Overall Rating                                      #####
#########
#########                               <  1  2  3  4  5  >
#########
####################################################################################################
```

### Product Details

```
####################################################################################################
#########
#########                    ################################## Search
#########
#########          Product Name                             ####################
#########                                                   ####################
#########          Manufacturer/Provider                    ####################
#########          Details/Blurb                            ####################
#########                                                   ####################
#########                                                   ####################
#########                                                   ####################
#########                                                   ####################
#########
#########          Ingredients/Contents
#########
#########           ######  Element 1
#########           ######    Overall Rating                           Amount
#########           ######    Health Rating    Sustainability Rating  %/units/trace
#########                     Supplier/Source information
#########
#########           ######  Element 2
#########           ######    Overall Rating                           Amount
#########           ######    Health Rating    Sustainability Rating  %/units/trace
#########                     Supplier/Source information
#########
####################################################################################################
```

We can almost read the React components and DAO objects off the ASCII art.
The Java backend is a basic CRUD and search service over MongoDB datastore.
Write operations are available via the API only.

Final architecture may end up looking more like a read optimised store and an injestion pipeline.
