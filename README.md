# service-conventions

Every guestgraph service has the same shape by check, not by hand. `SERVICE.md` is the list of
what a service has, whatever it is written in; a directory per stack, `spring/` today, holds the
files that make the list true for that stack: the parent build every service's `pom.xml` takes by
path, the source rules, the architecture rules as one test class, the diagram script, the
workflow, the sync script, and the block a service's agent file opens with after the family's.
Every decision lives in the engine repository, under
[`specs/007-service-conventions/`](https://github.com/guestgraph/engine/tree/main/specs/007-service-conventions),
and this repository builds what that specification says.

## How a service takes a release

A service names the release in `service-conventions.json` at its root, with its stack, its
package root, the parameter every repository query carries and the schema its diagram is drawn
from:

```json
{ "repo": "guestgraph/service-conventions", "tag": "v0.1.0", "stack": "spring",
  "root": "io.guestgraph.engine", "scope": "tenantId", "schema": "engine", "jdbcClientAllowed": [] }
```

`sh service-conventions/service-conventions-sync sync` writes `service-conventions/` from the
pinned release and puts three of its files where a build reads them: the workflow in
`.github/workflows/verify.yml`, the rules test in `src/test/java/ServiceRulesTest.java`, and the
block in `AGENTS.md`. The service's `pom.xml` names `service-conventions/pom.xml` as its parent by
`relativePath`, at the version the tag names. `sh service-conventions/service-conventions-sync
check` says whether the copy still matches the release, and the workflow runs it on every pull
request beside the service's own suite and the family's prose check. The pin is editorial: it
moves when the owner decides, in a commit that says why, and a service that is behind is behind
on purpose.

## How a rule changes

In this repository, once: edit the stack's file, run `sh tests/run`, open a pull request, and tag
the release with notes in the prose register. A change to any file under a stack's directory is
at least a minor release, because it makes every service's copy stale; a change that asks a
service to do more than re-sync is a major, and the notes say which. A service then moves its
pin and re-syncs, and nothing else.

## How a new service starts

`sh new-service spring <name> [directory]` writes a service of the Spring stack that passes the
family's sync check, the stack's sync check and the service check on its first run: the pin with
the root package and the schema derived from the name, both vendored sets, a `pom.xml` naming the
parent, the application class, the `api` package with the document controller and the size
filter, the configuration with its local profile, the compose file, an empty contract, a first
migration, the diagram, the README with its required sections and the agent file. It leaves two
things to the person: the Maven wrapper, `mvn -N wrapper:wrapper`, and the paragraph in the README
that says what the service is for, which no script can write.

## Building and checking

`sh tests/run` holds the scripts to their fixtures: a service equal to its pin passes, one
character of drift fails naming the file, a missing file fails naming it. `sh
conventions/conventions-check` holds the prose to the family's conventions, and `AGENTS.md` says
how to work here.
