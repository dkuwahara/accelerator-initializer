{
    parser: "babel-eslint",
    "extends": ["eslint:recommended", "plugin:react/recommended"],
    "rules": {
        "react/prop-types": 0
    },
    "env": {
        "browser": true,
        "node": true
    },
    "globals": {"Promise": true}
}