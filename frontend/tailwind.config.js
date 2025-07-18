/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class',
  content: [

    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors:{
        'jet-black':'#000000'
      }
    },
  },
  plugins: [],
}

