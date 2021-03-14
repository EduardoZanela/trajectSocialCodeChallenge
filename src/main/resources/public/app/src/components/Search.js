  
import './Search.css'

function Search(props) {
  const onChange = e => {
    props.onChange(e.target.value, e.key)
  }
  return (
    <input
      className="search"
      type="text"
      placeholder="Search for a keyword..."
      onKeyDown={onChange}
    />
  )
}

export default Search