# What every guestgraph service has

A guestgraph service is a process that serves an API for the guest graph or feeds it, and every
one of them has the same shape, whatever it is written in, so that a person who has run one can
run the next. This is the list. A stack's directory beside this file, `spring/` today, holds the
files that make the list true for that stack and a check that reads a service against it.

An **API document** served at `/api-docs` without a credential: what lies under the service's
resources in a folder named for the API, and nothing else, each document with its source named
beside it, a frozen record in the same repository, another repository at a pinned commit, or the
service's own, so a reader finds the API where the configuration is and a copy can never differ
from what it stands for.

A **health endpoint** at `/actuator/health` without a credential, and nothing else exposed there,
so a platform can poll the service and learns nothing from it.

Errors answered as **problem details**, so a caller reads every refusal the same way.

A **cap on the size of a request body**, answered as a problem detail, so a payload stored
forever is never larger than the service meant to store.

**One schema and one role**: the service creates its tables in a schema of its own, `DATABASE_SCHEMA`
with the service's name as the default, and connects as a role that owns that schema and nothing
else, so one database or two is the deployment's choice and no service reads another's tables.

A **diagram of its tables** committed beside the migrations and regenerated from them, held by a
drift check, so the picture of the schema is never older than the schema.

A **local profile** that starts the service against a seeded setup with nothing written by hand
first, so a developer runs it in one command.

A **package root** of `io.guestgraph.` followed by the repository's name, hyphens as dots, with the
endpoints, filters and error answers under `api` below it, so the same thing is found in the same
place in every service.

A **README** that says what the service does, how to run it, the deployment paragraph with the two
statements that create its role and schema, and the checks it runs.

An **agent file** that opens with the family's block, then the stack's block, then the service's
own text, so an agent reads the shared rules before the service's.
