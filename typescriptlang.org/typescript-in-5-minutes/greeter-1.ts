/*
 * https://www.typescriptlang.org/docs/handbook/typescript-in-5-minutes.html
*/

function greeter(person: string) {
    return "Hello, " + person;
}

let user = "Jane User";

//let user = [0, 1, 2];

document.body.innerHTML = greeter(user);