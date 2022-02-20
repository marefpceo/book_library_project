import { useRef, useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom'

const LoginForm = () => {
  const userRef = useRef();
  const errRef = useRef();

  const [user, setUser] = useState('');
  const [pwd, setPwd] = useState('');
  const [errMsg, setErrMsg] = useState('');
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    userRef.current.focus();
  }, [])

  useEffect(() => {
    setErrMsg('');
  }, [user, pwd])

  const handleSubmit = async (e) => {
    e.preventDefault();


  }

  return (
    <section>
      <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
      <h1 className="text-center text-4xl text-gray-500 mb-10">Sign In</h1>
      <form onSubmit={handleSubmit} className="w-128 flex justify-center flex-col self-center shadow-md bg-white rounded px-8 pt-6 pb-8 mb-4 font-['Roboto]">
        <label htmlFor="username" className="block text-gray-700 text-sm mb-2">Username</label>
        <input 
          type="text" 
          id="username"
          ref={userRef}
          autoComplete="off"
          onChange={(e) => setUser(e.target.value)}
          value={user}
          required
          className={
            "w-full shadow appearance-none border rounded py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline placeholder-red-700 text-base rounded-lg "
          }
        />
        <label htmlFor="password" className="block text-gray-700 text-sm mb-2">Password</label>
        <input 
          type="password" 
          id="password"
          onChange={(e) => setPwd(e.target.value)}
          value={pwd}
          required
          className={
            "w-full shadow appearance-none border rounded py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline placeholder-red-700 text-base rounded-lg "
          }
        />
        <button className="mt-6 text-white w-full rounded-full bg-blue-500 hover:bg-blue-700:text-white font-bold py-2 px-4 round">Sign In</button>
      </form>
      <p className="flex mt-14 justify-center">
        Need an Account?
        <br />
        <span className="pl-2">
          <Link
            to="/register"
            className="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-300 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-gray-900 dark:hover:bg-gray-700 dark:hover:text-gray-300 md:dark:hover:bg-transparent dark:border-gray-700"
          >
            Register
          </Link>
        </span>
      </p>
    </section>
  )
}

export default LoginForm