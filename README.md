# service-conventions

Every guestgraph service has the same shape by check, not by hand. This repository holds the
list of what a service has, whatever its stack, and one directory per stack with the files that
make it true: a parent build, the source rules, the architecture rules, the diagram script, the
workflow, and the two checks a service runs beside its own suite. A service vendors its stack's
directory at a pinned release, moves the pin on purpose, and fails its checks when its copy
differs from the pin or when it lacks an item of the list. Every decision lives in the engine
repository, under
[`specs/007-service-conventions/`](https://github.com/guestgraph/engine/tree/main/specs/007-service-conventions),
and this repository builds what that specification says.
