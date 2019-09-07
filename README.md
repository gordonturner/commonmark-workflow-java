# commonmark-workflow-java


Running Release
---------------

- Run jar release, for pdf:

```
java -jar ./commonmark-workflow-2.0-jar-with-dependencies.jar -p ./sample-docs/spec.md
```

- Run jar release, for doc:

```
java -jar ./commonmark-workflow-2.0-jar-with-dependencies.jar -d ./sample-docs/spec.md
```




Building From Source
--------------------

- Build:

```
mvn clean install
```

- Run jar after build, for pdf:

```
java -jar ./target/commonmark-workflow-2.0-SNAPSHOT-jar-with-dependencies.jar -p ./sample-docs/spec.md
```


- Run jar after build, for doc:

```
java -jar ./target/commonmark-workflow-2.0-SNAPSHOT-jar-with-dependencies.jar -d ./sample-docs/spec.md
```




