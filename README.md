# ScalaTowerDefense
PPS project, MSc UNIBO.

# Run test with docker
#### Build and creare image using the dockerfile
```
docker build -t tower-defense --target test .
```

#### Run test
```
docker run -it --rm --name tower-defense-test tower-defense
```