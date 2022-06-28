# PPS - Scala Tower Defense
Paradigmi di Programmazione e Sviluppo project, MSc UNIBO.

## Execute Jar
- Dowload jar from https://github.com/aismam/ScalaTowerDefense/releases/tag/v4.0.0
- Java 17 or newest is required
- java -jar ./towerdefense-4.0.0-all.jar

## Project Contribution

#### CLone project
```
git clone https://github.com/aismam/ScalaTowerDefense.git
```

#### Move to dev branch
```
git checkout dev
```

#### Pull for get all change
```
git pull
```
#### For propose new change or fix, you need to create a new branch from dev branch 
```
git checkout -b new branch
```

#### Push your change or fix
```
git add .
git commit -m "your message"
git push ...
```

#### Finally, create Pull Request using github interface

## Run test with docker
#### Build and creare image using the dockerfile
```
docker build -t tower-defense --target test .
```

#### Run test
```
docker run -it --rm --name tower-defense-test tower-defense
```

## For every PR, before merge on dev, all tests must pass.

## Semantic versioning
We use plugin [Glovo gradle versioning plugin](https://github.com/Glovo/gradle-versioning-plugin) to manage version
#### Usage
```
get current version
./gradlew printVersion

for minor release
./gradlew incrementSemanticVersion

for Major release 
./gradlew incrementSemanticVersion --major
```

## Perform a release
To create a release starting from the main branch:

```
git tag -a $current_version -m "Release for $current_version"
git push --tags
```
After you have created the tag and pushed, the GitHub actions will automatically create the release and deploy the jar to the server
