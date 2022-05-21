# ScalaTowerDefense
PPS project, MSc UNIBO.

# Project Contribution

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

# Run test with docker
#### Build and creare image using the dockerfile
```
docker build -t tower-defense --target test .
```

#### Run test
```
docker run -it --rm --name tower-defense-test tower-defense
```

## For every PR, before merge on dev, all tests must pass.